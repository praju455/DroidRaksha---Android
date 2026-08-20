package com.droidraksha.mobile.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.ui.theme.*

@Composable
fun DeviceSecurityGauge(
    totalApps: Int,
    criticalCount: Int,
    highCount: Int,
    mediumCount: Int,
    safeCount: Int,
    deviceRiskScore: Int,
    isScanning: Boolean,
    onStartScan: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Health score: 100 is perfectly clean, 0 is heavily infected
    val healthScore = (100 - deviceRiskScore).coerceIn(0, 100)

    val (themeColor, statusText, statusBadge) = when {
        criticalCount > 0 -> Triple(RiskCritical, "CRITICAL RISK", "Immediate Action Required")
        highCount > 0 -> Triple(RiskHigh, "HIGH THREATS", "$highCount High-Risk Apps Found")
        mediumCount > 0 -> Triple(RiskMedium, "WARNINGS", "$mediumCount Apps With Alerts")
        else -> Triple(ShieldCyan, "SYSTEM SECURE", "Real-Time Shield Active")
    }

    val infiniteTransition = rememberInfiniteTransition(label = "Pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseAlpha"
    )

    var animationPlayed by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { animationPlayed = true }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) healthScore / 100f else 0f,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "GaugeSweep"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF131D31),
                        Color(0xFF0A0F1D)
                    )
                )
            )
            .border(1.dp, themeColor.copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Top Status Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(themeColor.copy(alpha = pulseAlpha))
                    )
                    Text(
                        text = statusText,
                        color = themeColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(themeColor.copy(alpha = 0.12f))
                        .border(0.5.dp, themeColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = statusBadge,
                        color = themeColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Circular Cyber Tachometer Gauge
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(160.dp)
                    .padding(6.dp)
            ) {
                Canvas(modifier = Modifier.size(150.dp)) {
                    val strokeWidth = 12.dp.toPx()
                    val diameter = size.minDimension - strokeWidth
                    val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)

                    // Outer faint cyber ring
                    drawArc(
                        color = Color(0xFF1E293B),
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(diameter, diameter),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Active Glowing Progress Arc
                    drawArc(
                        color = themeColor,
                        startAngle = 135f,
                        sweepAngle = animatedProgress * 270f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(diameter, diameter),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$healthScore",
                        color = TextPrimary,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "SAFETY SCORE",
                        color = TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Quick Metrics Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0F172A).copy(alpha = 0.7f))
                    .border(0.5.dp, ShieldNavyBorder, RoundedCornerShape(14.dp))
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MetricItem(label = "Total Apps", value = "$totalApps", color = TextPrimary)
                Box(modifier = Modifier.width(1.dp).height(24.dp).background(ShieldNavyBorder))
                MetricItem(label = "Protected", value = "$safeCount", color = RiskLow)
                Box(modifier = Modifier.width(1.dp).height(24.dp).background(ShieldNavyBorder))
                MetricItem(
                    label = "Flagged",
                    value = "${criticalCount + highCount + mediumCount}",
                    color = if (criticalCount + highCount > 0) RiskCritical else RiskLow
                )
            }

            // Cyber Scan Button
            Button(
                onClick = onStartScan,
                enabled = !isScanning,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShieldCyan,
                    disabledContainerColor = ShieldNavyBorder
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                if (isScanning) {
                    CircularProgressIndicator(color = ShieldNavyDark, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Analyzing Installed Packages...", color = ShieldNavyDark, fontWeight = FontWeight.Bold)
                } else {
                    Icon(Icons.Default.Security, contentDescription = null, tint = ShieldNavyDark, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Run Full Device Threat Scan", color = ShieldNavyDark, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun MetricItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        Text(label, color = TextMuted, fontSize = 10.sp)
    }
}
