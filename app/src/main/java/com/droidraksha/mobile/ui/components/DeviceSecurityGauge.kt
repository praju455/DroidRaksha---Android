package com.droidraksha.mobile.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
    onStartScan: () -> Unit, // Keeping signature for backward compatibility, but won't render button here
    modifier: Modifier = Modifier
) {
    val healthScore = (100 - deviceRiskScore).coerceIn(0, 100)

    val (themeColor, statusText, statusBadge) = when {
        criticalCount > 0 -> Triple(RiskCritical, "CRITICAL RISK", "Immediate Action Required")
        highCount > 0 -> Triple(RiskHigh, "HIGH THREATS", "$highCount High-Risk Apps Found")
        mediumCount > 0 -> Triple(RiskMedium, "WARNINGS", "$mediumCount Apps With Alerts")
        else -> Triple(RiskSafe, "SYSTEM SECURE", "Real-Time Shield Active")
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

    GlassCard(
        modifier = modifier.fillMaxWidth().clickable { onStartScan() },
        contentPadding = 24.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
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
                        style = Typography.labelSmall,
                        letterSpacing = 1.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(themeColor.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = statusBadge,
                        color = themeColor,
                        style = Typography.labelSmall
                    )
                }
            }

            // Circular Cyber Tachometer Gauge
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(180.dp)
                    .padding(6.dp)
            ) {
                Canvas(modifier = Modifier.size(160.dp)) {
                    val strokeWidth = 14.dp.toPx()
                    val diameter = size.minDimension - strokeWidth
                    val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)

                    // Outer faint cyber ring
                    drawArc(
                        color = DividerHairline,
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(diameter, diameter),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Active Glowing Progress Arc (Gradient)
                    val gradientBrush = Brush.sweepGradient(
                        colors = listOf(AccentCyan, AccentCyan, AccentCyan),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                    
                    val brushToUse = if (healthScore > 80) gradientBrush else SolidColor(themeColor)

                    drawArc(
                        brush = brushToUse,
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
                        fontSize = 48.sp, // Oversized numeral
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-2).sp
                    )
                    Text(
                        text = "SAFETY SCORE",
                        color = TextSecondary,
                        style = Typography.labelSmall,
                        letterSpacing = 1.sp
                    )
                }
            }

        }
    }
}

@Composable
private fun MetricItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(label, color = TextMuted, style = Typography.labelSmall)
    }
}
