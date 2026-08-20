package com.droidraksha.mobile.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import com.droidraksha.mobile.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(
    viewModel: ScanHistoryViewModel,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BackgroundSurface, BackgroundDark)
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(backgroundBrush),
        topBar = {
            TopAppBar(
                title = { Text("Scan History & Audit Log", color = TextPrimary, style = Typography.titleLarge, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        if (state.sessions.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Text("No previous scan sessions found.", color = TextMuted, fontSize = 14.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(state.sessions, key = { it.id }) { session ->
                    com.droidraksha.mobile.ui.components.GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = 20.dp
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = dateFormat.format(Date(session.scanCompletedAt)),
                                    color = TextPrimary,
                                    style = Typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Device Score: ${session.deviceOverallScore}",
                                    color = if (session.deviceOverallScore > 40) RiskHigh else RiskSafe,
                                    fontWeight = FontWeight.Bold,
                                    style = Typography.labelSmall
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Triggered by: ${session.triggeredBy.replaceFirstChar { it.uppercase() }}",
                                color = TextMuted,
                                style = Typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                HistoryStat("Scanned", session.totalAppsScanned.toString(), TextSecondary)
                                HistoryStat("Critical", session.criticalCount.toString(), RiskCritical)
                                HistoryStat("High", session.highCount.toString(), RiskHigh)
                                HistoryStat("Medium", session.mediumCount.toString(), RiskMedium)
                                HistoryStat("Safe", session.safeCount.toString(), RiskSafe)
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }
    }
}

@Composable
private fun HistoryStat(label: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = TextMuted, style = Typography.labelSmall)
        Text(value, color = color, style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}
