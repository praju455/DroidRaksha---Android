package com.droidraksha.mobile.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.domain.model.AppInfo
import com.droidraksha.mobile.domain.model.RiskLevel
import com.droidraksha.mobile.ui.theme.*

@Composable
fun InteractiveScoreboard(
    app: AppInfo,
    modifier: Modifier = Modifier
) {
    val (primaryColor, bgColor) = when (app.riskLevel) {
        RiskLevel.CRITICAL -> Pair(RiskCritical, RiskCritical.copy(alpha = 0.1f))
        RiskLevel.HIGH -> Pair(RiskHigh, RiskHigh.copy(alpha = 0.1f))
        RiskLevel.MEDIUM -> Pair(RiskMedium, RiskMedium.copy(alpha = 0.1f))
        RiskLevel.LOW -> Pair(RiskLow, RiskLow.copy(alpha = 0.1f))
        RiskLevel.SAFE -> Pair(RiskSafe, RiskSafe.copy(alpha = 0.1f))
    }

    var isExpanded by remember { mutableStateOf(true) }
    var animationPlayed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    val animatedScoreProgress by animateFloatAsState(
        targetValue = if (animationPlayed) app.riskScore / 100f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "ScoreGaugeAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardLevel2)
            .border(1.dp, primaryColor.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            .padding(18.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Header: App Title & Risk Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Forensic Scoreboard",
                        color = TextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                RiskBadge(level = app.riskLevel, score = app.riskScore)
            }

            // Circular Interactive Score Gauge
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(130.dp)
                    .padding(4.dp)
            ) {
                Canvas(modifier = Modifier.size(120.dp)) {
                    val strokeWidth = 10.dp.toPx()
                    val diameter = size.minDimension - strokeWidth
                    val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)

                    // Track Circle
                    drawArc(
                        color = Color(0xFF1E293B),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(diameter, diameter),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Active Score Arc
                    drawArc(
                        color = primaryColor,
                        startAngle = -90f,
                        sweepAngle = animatedScoreProgress * 360f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(diameter, diameter),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${(animatedScoreProgress * 100).toInt()}",
                        color = primaryColor,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "/ 100",
                        color = TextMuted,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Threat Categories Tags
            if (app.threatCategories.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    app.threatCategories.take(3).forEach { cat ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(RiskCritical.copy(alpha = 0.15f))
                                .border(0.5.dp, RiskCritical.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(cat, color = RiskCritical, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            // Score Breakdown Bars
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.TrendingUp, contentDescription = null, tint = AccentCyan, modifier = Modifier.size(16.dp))
                        Text("Risk Score Breakdown", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Text(
                        text = if (isExpanded) "Hide" else "Show Details",
                        color = AccentCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                if (isExpanded) {
                    val yaraVal = if (app.riskScore > 60) 25 else 0
                    val iocVal = if (app.isFakeUpi || app.isFakeBank) 20 else 0
                    val c2Val = app.c2ConfidenceScore
                    val permVal = if (app.dangerousPermissions.isNotEmpty()) minOf(10, app.dangerousPermissions.size * 2) else 0
                    val certVal = if (app.isSelfSigned || app.isDebugCert) 5 else 0
                    val onnxVal = if (app.isAnomalyFlagged) 5 else 0

                    ScoreBreakdownBar(label = "Permissions", value = permVal, maxValue = 10, primaryColor = primaryColor)
                    ScoreBreakdownBar(label = "YARA Signatures", value = yaraVal, maxValue = 30, primaryColor = primaryColor)
                    ScoreBreakdownBar(label = "India IOC", value = iocVal, maxValue = 30, primaryColor = primaryColor)
                    ScoreBreakdownBar(label = "C2 Signals", value = c2Val, maxValue = 40, primaryColor = primaryColor)
                    ScoreBreakdownBar(label = "Certificate", value = certVal, maxValue = 5, primaryColor = primaryColor)
                    ScoreBreakdownBar(label = "ONNX Anomaly", value = onnxVal, maxValue = 5, primaryColor = primaryColor)
                }
            }
        }
    }
}

@Composable
private fun ScoreBreakdownBar(
    label: String,
    value: Int,
    maxValue: Int,
    primaryColor: Color
) {
    val pct = (value.toFloat() / maxValue).coerceIn(0f, 1f)
    val animatedWidth by animateFloatAsState(
        targetValue = pct,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "BarAnim_$label"
    )

    val barColor = when {
        pct > 0.66f -> RiskCritical
        pct > 0.33f -> RiskHigh
        pct > 0f -> RiskLow
        else -> DividerHairline
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, color = TextMuted, fontSize = 11.sp, modifier = Modifier.width(95.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color(0xFF0F172A))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedWidth)
                    .clip(RoundedCornerShape(3.dp))
                    .background(barColor)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$value/$maxValue",
            color = if (value > 0) TextPrimary else TextMuted,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.width(42.dp)
        )
    }
}
