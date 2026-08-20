package com.droidraksha.mobile.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.domain.model.AppInfo
import com.droidraksha.mobile.domain.model.RiskLevel
import com.droidraksha.mobile.ui.components.Level1Card
import com.droidraksha.mobile.ui.components.AppRowItem
import com.droidraksha.mobile.ui.components.DeviceSecurityGauge
import com.droidraksha.mobile.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onNavigateToAppList: (String?) -> Unit,
    onNavigateToAppDetail: (String) -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToLiveScan: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = BackgroundSurface, // Pure black background
        floatingActionButton = {
            // Neon Cyan solid FAB for Live Scan
            FloatingActionButton(
                onClick = {
                    viewModel.startScan()
                    onNavigateToLiveScan()
                },
                shape = CircleShape,
                containerColor = AccentCyan,
                contentColor = Color.Black,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Radar,
                    contentDescription = "Live Scan",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Top Window Bar (macOS style + Breadcrumb)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Breadcrumb
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = AccentCyan, modifier = Modifier.size(14.dp))
                            Text(
                                text = "DROID.RAKSHA / LOCAL_DEVICE",
                                color = TextPrimary,
                                style = Typography.bodySmall
                            )
                        }
                    }

                    // Live Feed Badge
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color(0x22FFFFFF), RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(AccentCyan))
                        Text("LIVE FEED", color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Security Score Gauge
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    DeviceSecurityGauge(
                        totalApps = state.totalApps,
                        criticalCount = state.criticalCount,
                        highCount = state.highCount,
                        mediumCount = state.mediumCount,
                        safeCount = state.safeCount,
                        deviceRiskScore = state.deviceRiskScore,
                        isScanning = state.isScanning,
                        onStartScan = {
                            viewModel.startScan()
                            onNavigateToLiveScan()
                        }
                    )
                }
            }

            // Horizontal Metric Box
            item {
                Level1Card(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = 16.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MiniMetric(title = "TOTAL THREATS", value = "${state.criticalCount + state.highCount}", color = AccentCyan)
                        Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color(0x1AFFFFFF)))
                        MiniMetric(title = "SAFETY SCORE", value = "${(100 - state.deviceRiskScore).coerceIn(0, 100)}", color = TextPrimary)
                        Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color(0x1AFFFFFF)))
                        MiniMetric(title = "SCANNED APPS", value = "${state.totalApps}", color = RiskSafe)
                    }
                }
            }

            // Threat Intelligence Feed
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Threat Feed",
                        color = TextPrimary,
                        style = Typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { onNavigateToAppList(null) }) {
                        Text("View All", color = AccentCyan, style = Typography.labelSmall)
                    }
                }
            }

            if (state.topThreats.isNotEmpty()) {
                items(state.topThreats) { app ->
                    Level1Card(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = 16.dp
                    ) {
                        AppRowItem(app = app, onClick = { onNavigateToAppDetail(app.packageName) })
                    }
                }
            } else {
                item {
                    Level1Card(modifier = Modifier.fillMaxWidth(), contentPadding = 16.dp) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = RiskSafe)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("No active threats detected.", color = TextSecondary, style = Typography.bodyMedium)
                        }
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    subtitle: String? = null,
    trendText: String? = null,
    trendColor: Color = RiskSafe,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    progress: Float? = null,
    progressColor: Color = AccentCyan
) {
    Level1Card(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    color = TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = value,
                    color = TextPrimary,
                    style = Typography.displayLarge,
                    fontSize = 40.sp,
                    lineHeight = 40.sp
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = subtitle,
                        color = TextSecondary,
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (trendText != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (trendColor == RiskSafe) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                        contentDescription = null,
                        tint = trendColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(trendText, color = trendColor, fontSize = 12.sp)
                }
            }
            
            if (progress != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0x1AFFFFFF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(progressColor)
                    )
                }
            }
        }
    }
}

@Composable
fun EventLogItem(tag: String, message: String, tagColor: Color) {
    val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(time, color = Color(0xFF3F3F46), style = Typography.bodySmall) // Very muted dark grey
        Spacer(modifier = Modifier.width(12.dp))
        Text(tag, color = tagColor, style = Typography.bodySmall)
        Spacer(modifier = Modifier.width(12.dp))
        Text(message, color = TextSecondary, style = Typography.bodySmall)
    }
}

@Composable
fun MiniMetric(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, style = Typography.displaySmall, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
    }
}
