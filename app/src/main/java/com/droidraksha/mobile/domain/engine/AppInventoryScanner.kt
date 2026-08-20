package com.droidraksha.mobile.domain.engine

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import com.droidraksha.mobile.data.local.entity.AppEntity
import com.droidraksha.mobile.domain.model.InstallSource
import com.droidraksha.mobile.domain.model.RiskLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Layer 1 Engine — App Inventory Scanner.
 *
 * Uses [PackageManager] to enumerate every installed application and
 * extract the metadata fields required by the subsequent detection engines.
 *
 * Runs on a background dispatcher (caller's responsibility).
 */
@Singleton
class AppInventoryScanner @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val pm: PackageManager = context.packageManager

    /**
     * Returns a list of partial [AppEntity] objects with raw metadata filled in.
     * Risk scoring fields (riskScore, c2Verdict, etc.) are left at defaults — they
     * will be populated by [ScanOrchestrator] after the other engines have run.
     */
    fun scanInstalledApps(): List<AppEntity> {
        val flagsInt = PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS
        val packages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.getInstalledPackages(PackageManager.PackageInfoFlags.of(flagsInt.toLong()))
        } else {
            @Suppress("DEPRECATION")
            pm.getInstalledPackages(flagsInt)
        }

        return packages.mapNotNull { pkgInfo ->
            val appInfo = pkgInfo.applicationInfo ?: return@mapNotNull null
            
            // Skip the DroidRaksha app itself from its own scan results
            if (pkgInfo.packageName == context.packageName) return@mapNotNull null

            // Determine install source safely
            val installSource = runCatching { detectInstallSource(pkgInfo.packageName, appInfo) }
                .getOrDefault(InstallSource.UNKNOWN)

            // Certificate info safely
            val certInfo = runCatching { extractCertInfo(pkgInfo.packageName) }
                .getOrDefault(Triple("Unknown", "Unknown", true))

            // Permissions
            val allPerms = pkgInfo.requestedPermissions?.toList() ?: emptyList()
            
            val appName = runCatching { pm.getApplicationLabel(appInfo).toString() }.getOrDefault(pkgInfo.packageName)
            val apkSize = runCatching { java.io.File(appInfo.sourceDir).length() }.getOrDefault(0L)

            AppEntity(
                packageName = pkgInfo.packageName,
                appName = appName,
                versionName = pkgInfo.versionName ?: "unknown",
                versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                    pkgInfo.longVersionCode else pkgInfo.versionCode.toLong(),
                installedAt = pkgInfo.firstInstallTime,
                lastUpdated = pkgInfo.lastUpdateTime,
                apkSizeBytes = apkSize,
                targetSdkVersion = appInfo.targetSdkVersion,
                minSdkVersion = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    appInfo.minSdkVersion else 1,
                installSource = installSource.name,
                certIssuer = certInfo.first,
                certSubject = certInfo.second,
                isSelfSigned = certInfo.third,
                isDebugCert = (appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0,

                // Placeholder values
                riskScore = 0,
                riskLevel = RiskLevel.SAFE.name,
                threatCategories = "[]",
                isFakeUpi = false,
                isFakeBank = false,
                isLoanScam = false,
                matchedIocDomains = "[]",
                dangerousPermissions = "[]",
                dangerousComboFlags = "[]",
                totalPermissionCount = allPerms.size,
                c2Verdict = "NONE",
                c2ConfidenceScore = 0,
                detectedC2Frameworks = "[]",
                onnxPredictedClass = "Unknown",
                onnxConfidence = 0f,
                isAnomalyFlagged = false,
                lastScannedAt = System.currentTimeMillis(),
                deepScanAvailable = false,
            )
        }
    }

    private fun detectInstallSource(packageName: String, appInfo: ApplicationInfo): InstallSource {
        // System apps are excluded from Play Store / sideloaded classification
        if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
            return InstallSource.UNKNOWN
        }

        // Check installer package name
        val installer = runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                pm.getInstallSourceInfo(packageName).installingPackageName
            } else {
                @Suppress("DEPRECATION")
                pm.getInstallerPackageName(packageName)
            }
        }.getOrNull()

        return when {
            installer == null -> InstallSource.SIDELOADED
            installer.contains("com.android.vending") ||
            installer.contains("com.google.android.packageinstaller") -> InstallSource.PLAY_STORE
            installer.contains("adb") ||
            installer.contains("com.android.shell") -> InstallSource.ADB
            else -> InstallSource.SIDELOADED
        }
    }

    /**
     * Extracts certificate subject / issuer from the APK signing certificate.
     * Returns Triple(issuer, subject, isSelfSigned).
     */
    private fun extractCertInfo(packageName: String): Triple<String, String, Boolean> {
        return runCatching {
            val sigs = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val sigInfo = pm.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                )
                sigInfo.signingInfo?.apkContentsSigners
            } else {
                @Suppress("DEPRECATION")
                val sigInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                @Suppress("DEPRECATION")
                sigInfo.signatures
            }

            if (sigs.isNullOrEmpty()) return Triple("Unknown", "Unknown", true)

            val certBytes = sigs[0].toByteArray()
            val cert = java.security.cert.CertificateFactory.getInstance("X.509")
                .generateCertificate(java.io.ByteArrayInputStream(certBytes))
                as java.security.cert.X509Certificate

            val issuer = cert.issuerX500Principal.name
            val subject = cert.subjectX500Principal.name
            val isSelfSigned = issuer == subject

            Triple(issuer, subject, isSelfSigned)
        }.getOrDefault(Triple("Unknown", "Unknown", true))
    }

    /** Returns the package names of all currently installed apps (for pruning stale DB entries). */
    fun getCurrentPackageNames(): List<String> =
        pm.getInstalledPackages(0).map { it.packageName }
}
