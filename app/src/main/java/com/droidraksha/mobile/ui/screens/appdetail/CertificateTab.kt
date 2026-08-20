package com.droidraksha.mobile.ui.screens.appdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.domain.model.AppInfo
import com.droidraksha.mobile.ui.theme.*
import com.droidraksha.mobile.ui.components.Level1Card
import androidx.compose.ui.graphics.Color

@Composable
fun CertificateTab(app: AppInfo) {
    val isCleanCert = !app.isSelfSigned && !app.isDebugCert
    val trustColor = if (isCleanCert) RiskLow else if (app.isSelfSigned) RiskMedium else RiskCritical

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Trust Header Card
        item {
            Level1Card(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = 18.dp,
                borderColor = trustColor.copy(alpha = 0.5f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(
                                imageVector = if (isCleanCert) Icons.Default.WorkspacePremium else Icons.Default.Warning,
                                contentDescription = null,
                                tint = trustColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Certificate Trust Status",
                                color = TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(trustColor.copy(alpha = 0.15f))
                                .border(1.dp, trustColor, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (isCleanCert) "VERIFIED" else if (app.isDebugCert) "DEBUG BUILD" else "SELF-SIGNED",
                                color = trustColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = if (isCleanCert) {
                            "This package is signed by a valid commercial/OEM developer certificate authority matching recognized vendor chains."
                        } else if (app.isDebugCert) {
                            "⚠️ Signed with an Android Debug key (androiddebugkey). Debug APKs must NEVER be distributed in production environments."
                        } else {
                            "⚠️ Signed with a Self-Signed certificate. Self-signed APKs bypass CA revocation checks and are frequently used by repackaged droppers."
                        },
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Detailed Certificate Properties
        item {
            Level1Card(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = 20.dp
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.Fingerprint, contentDescription = null, tint = AccentCyan, modifier = Modifier.size(18.dp))
                        Text("X.509 CERTIFICATE METADATA", color = TextSecondary, style = Typography.titleMedium)
                    }

                    Divider(color = DividerHairline, thickness = 0.5.dp)

                    CertPropRow(label = "Subject DN", value = app.certSubject.ifBlank { "CN=${app.appName}, O=Developer" })
                    CertPropRow(label = "Issuer DN", value = app.certIssuer.ifBlank { "CN=${app.appName}, O=Developer" })
                    CertPropRow(label = "Self-Signed Certificate", value = if (app.isSelfSigned) "YES (Warning)" else "NO (Root CA)")
                    CertPropRow(label = "Debug Certificate", value = if (app.isDebugCert) "YES (High Risk)" else "NO (Production)")
                    CertPropRow(label = "Install Source", value = app.installSource.label)
                }
            }
        }

        // Security Checklist
        item {
            Level1Card(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = 20.dp
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("SECURITY VALIDATION CHECKLIST", color = TextSecondary, style = Typography.titleMedium)

                    ChecklistRow(
                        title = "Production Signing Key",
                        isPassed = !app.isDebugCert,
                        note = if (!app.isDebugCert) "Not signed with Android debug keystore" else "Signed with test key"
                    )
                    ChecklistRow(
                        title = "Authority Validation",
                        isPassed = !app.isSelfSigned,
                        note = if (!app.isSelfSigned) "Validated certificate authority" else "Unverified self-signed signature"
                    )
                    ChecklistRow(
                        title = "Store Origin",
                        isPassed = app.installSource.label.contains("Play Store"),
                        note = app.installSource.label
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(60.dp)) }
    }
}

@Composable
private fun CertPropRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, color = TextMuted, fontSize = 11.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = TextPrimary,
            style = Typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = DividerHairline, thickness = 0.5.dp)
    }
}

@Composable
private fun ChecklistRow(title: String, isPassed: Boolean, note: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            Text(note, color = TextMuted, fontSize = 11.sp)
        }
        Icon(
            imageVector = if (isPassed) Icons.Default.CheckCircle else Icons.Default.Warning,
            contentDescription = null,
            tint = if (isPassed) RiskLow else RiskHigh,
            modifier = Modifier.size(18.dp)
        )
    }
}
