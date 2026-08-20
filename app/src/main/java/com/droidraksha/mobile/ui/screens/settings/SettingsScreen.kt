package com.droidraksha.mobile.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import com.droidraksha.mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    var urlInput by remember(state.backendUrl) { mutableStateOf(state.backendUrl) }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BackgroundSurface, BackgroundDark)
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(backgroundBrush),
        topBar = {
            TopAppBar(
                title = { Text("Settings & Intelligence Config", color = TextPrimary, style = Typography.titleLarge, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Background Scanner Toggle
            SettingCard {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Periodic Background Scan", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Text("Runs on-device WorkManager scan every 24h & on app installs", color = TextMuted, style = Typography.bodyMedium)
                    }
                    Switch(
                        checked = state.backgroundScanEnabled,
                        onCheckedChange = { viewModel.setBackgroundScan(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = AccentCyan,
                            uncheckedThumbColor = TextSecondary,
                            uncheckedTrackColor = CardLevel2
                        )
                    )
                }
            }

            // Wi-Fi Only Sync Toggle
            SettingCard {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Wi-Fi Only for Deep Scan", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Text("Prevent cellular data usage when uploading IOC hashes for deep scan", color = TextMuted, style = Typography.bodyMedium)
                    }
                    Switch(
                        checked = state.wifiOnlySync,
                        onCheckedChange = { viewModel.setWifiOnlySync(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = AccentCyan,
                            uncheckedThumbColor = TextSecondary,
                            uncheckedTrackColor = CardLevel2
                        )
                    )
                }
            }

            // Backend Endpoint Configuration
            SettingCard {
                Column {
                    Text("Slim Backend Endpoint (FastAPI)", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text("Base URL used for on-demand /check-ioc AI deep scans", color = TextMuted, style = Typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = urlInput,
                        onValueChange = { urlInput = it },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = CardLevel2,
                            unfocusedContainerColor = CardLevel2,
                            focusedBorderColor = AccentCyan,
                            unfocusedBorderColor = DividerHairline,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { viewModel.updateBackendUrl(urlInput) },
                        colors = ButtonDefaults.buttonColors(containerColor = AccentCyan),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Save URL", color = BackgroundDark, fontWeight = FontWeight.Bold, style = Typography.labelSmall)
                    }
                }
            }

            // About DroidRaksha Card
            SettingCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("DroidRaksha Mobile v1.0.0", color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("Purpose-built for Smart India Hackathon (SIH260138)", color = AccentCyan, style = Typography.bodyMedium)
                    Text("Ministry of Power · Blockchain & Cybersecurity", color = TextSecondary, style = Typography.bodyMedium)
                    Text("BMS Institute of Technology and Management", color = TextMuted, style = Typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SettingCard(content: @Composable ColumnScope.() -> Unit) {
    com.droidraksha.mobile.ui.components.GlassCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 20.dp
    ) {
        Column { content() }
    }
}
