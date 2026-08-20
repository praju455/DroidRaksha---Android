package com.droidraksha.mobile.ui.screens.network

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.components.GlassCard
import com.droidraksha.mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkSecurityScreen(
    onNavigateBack: () -> Unit
) {
    var isVpnEnabled by remember { mutableStateOf(false) }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BackgroundSurface, BackgroundDark)
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(backgroundBrush),
        topBar = {
            TopAppBar(
                title = { Text("Network Security", color = TextPrimary, style = Typography.titleLarge, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Connection Status Card
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(RiskMedium.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Wifi, contentDescription = null, tint = RiskMedium)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Starbucks_Guest", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Unsecured Public Wi-Fi", color = RiskMedium, style = Typography.bodyMedium)
                    }
                }
            }

            // Warning Banner
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = RiskMedium.copy(alpha = 0.1f),
                borderColor = RiskMedium.copy(alpha = 0.3f)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = RiskMedium, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Traffic is exposed", color = RiskMedium, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Others on this network may be able to intercept your unencrypted data. Enable Secure VPN immediately.",
                            color = TextSecondary,
                            style = Typography.bodyMedium
                        )
                    }
                }
            }

            // VPN / DNS Toggle
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(CardLevel2),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.VpnKey, contentDescription = null, tint = AccentCyan)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Secure DNS & VPN", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(if (isVpnEnabled) "Protected Mode Active" else "Not Connected", color = TextSecondary, style = Typography.bodyMedium)
                        }
                    }
                    Switch(
                        checked = isVpnEnabled,
                        onCheckedChange = { isVpnEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = AccentCyan,
                            uncheckedThumbColor = TextSecondary,
                            uncheckedTrackColor = CardLevel2
                        )
                    )
                }
            }
        }
    }
}
