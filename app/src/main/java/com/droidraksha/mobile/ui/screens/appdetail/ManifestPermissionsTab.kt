package com.droidraksha.mobile.ui.screens.appdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Security
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

@Composable
fun ManifestPermissionsTab(app: AppInfo) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Manifest Overview Header
        item {
            SectionContainer(title = "📄 AndroidManifest Analysis", icon = Icons.Default.Code) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ManifestMetric(label = "Target SDK", value = "API ${app.targetSdkVersion}")
                    ManifestMetric(label = "Min SDK", value = "API ${app.minSdkVersion}")
                    ManifestMetric(label = "Total Permissions", value = "${app.totalPermissionCount}")
                }
            }
        }

        // Dangerous Combos Flagged
        item {
            SectionContainer(title = "⚡ Dangerous Permission Combos", icon = Icons.Default.Security) {
                if (app.dangerousComboFlags.isEmpty()) {
                    Text("No high-risk malicious permission combinations detected.", color = RiskLow, fontSize = 12.sp)
                } else {
                    app.dangerousComboFlags.forEach { combo ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(RiskCritical.copy(alpha = 0.1f))
                                .border(0.5.dp, RiskCritical.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = combo,
                                color = RiskCritical,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Declared Dangerous Permissions
        item {
            SectionContainer(title = "🔑 Declared Sensitive Permissions (${app.dangerousPermissions.size})", icon = Icons.Default.Key) {
                if (app.dangerousPermissions.isEmpty()) {
                    Text("No declared dangerous Android permissions.", color = RiskLow, fontSize = 12.sp)
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        app.dangerousPermissions.forEachIndexed { index, perm ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = perm.removePrefix("android.permission."),
                                    color = TextPrimary,
                                    style = Typography.bodySmall
                                )
                                com.droidraksha.mobile.ui.components.ThreatChip(text = "DANGEROUS", isWarning = true)
                            }
                            if (index < app.dangerousPermissions.size - 1) {
                                Divider(color = DividerHairline, thickness = 0.5.dp)
                            }
                        }
                    }
                }
            }
        }

        // Network Endpoints & IPs
        item {
            SectionContainer(title = "🌐 Network Endpoints & IOC Matches", icon = Icons.Default.Http) {
                if (app.matchedIocDomains.isEmpty()) {
                    Text("No known suspicious C2 or fraud domains matched.", color = RiskLow, fontSize = 12.sp)
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        app.matchedIocDomains.forEachIndexed { index, domain ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(domain, color = RiskCritical, style = Typography.bodySmall)
                                com.droidraksha.mobile.ui.components.ThreatChip(text = "FLAGGED IOC", isWarning = true)
                            }
                            if (index < app.matchedIocDomains.size - 1) {
                                Divider(color = DividerHairline, thickness = 0.5.dp)
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(60.dp)) }
    }
}

@Composable
private fun SectionContainer(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Level1Card(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 16.dp
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(icon, contentDescription = null, tint = AccentCyan, modifier = Modifier.size(18.dp))
                Text(title.uppercase(), color = TextSecondary, style = Typography.titleMedium)
            }
            Divider(color = DividerHairline, thickness = 0.5.dp)
            content()
        }
    }
}

@Composable
private fun ManifestMetric(label: String, value: String) {
    Column {
        Text(label, color = TextMuted, fontSize = 11.sp)
        Text(value, color = TextPrimary, style = Typography.bodySmall.copy(fontWeight = FontWeight.SemiBold, fontSize = 13.sp))
    }
}
