package com.droidraksha.mobile.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.theme.OrbCyan
import com.droidraksha.mobile.ui.theme.OrbBlue

@Composable
fun GuardianOrb(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    isScanning: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "orbTransition")
    
    // Breathing scale animation for the outer glow and core
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isScanning) 1.2f else 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isScanning) 800 else 2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orbScale"
    )

    // Rotation animation for orbit lines
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isScanning) 2500 else 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "orbRotation"
    )

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerOffset = Offset(this.size.width / 2, this.size.height / 2)
            val baseRadius = (this.size.minDimension / 2) * 0.7f 

            // Deep background radial glow (Electric Blue)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(OrbBlue.copy(alpha = 0.4f * scale), Color.Transparent),
                    center = centerOffset,
                    radius = baseRadius * 1.8f
                ),
                radius = baseRadius * 1.8f,
                center = centerOffset,
                blendMode = BlendMode.Screen
            )
            
            // Secondary inner glow (Cyan)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(OrbCyan.copy(alpha = 0.5f * scale), Color.Transparent),
                    center = centerOffset,
                    radius = baseRadius * 0.8f
                ),
                radius = baseRadius * 0.8f,
                center = centerOffset,
                blendMode = BlendMode.Screen
            )

            // Bright Cyan Core
            drawCircle(
                color = OrbCyan,
                radius = baseRadius * 0.15f * scale,
                center = centerOffset
            )
            
            // Core border ring
            drawCircle(
                color = OrbCyan.copy(alpha = 0.8f),
                radius = baseRadius * 0.25f * scale,
                center = centerOffset,
                style = Stroke(width = 1.dp.toPx())
            )

            // Animated concentric radar/orbit rings
            drawOrbitRing(centerOffset, baseRadius * 0.5f, rotation, OrbCyan.copy(alpha = 0.8f), 1.5f.dp)
            drawOrbitRing(centerOffset, baseRadius * 0.8f, -rotation * 0.8f, OrbBlue.copy(alpha = 0.6f), 1.dp)
            drawOrbitRing(centerOffset, baseRadius * 1.1f, rotation * 1.2f, OrbCyan.copy(alpha = 0.3f), 1.dp)
            
            if (isScanning) {
                drawOrbitRing(centerOffset, baseRadius * 1.4f, -rotation * 1.5f, OrbBlue.copy(alpha = 0.5f), 0.5f.dp)
            }
        }
    }
}

private fun DrawScope.drawOrbitRing(center: Offset, radius: Float, rotation: Float, color: Color, strokeWidth: Dp) {
    val sweepAngle1 = 160f
    val sweepAngle2 = 60f
    
    drawArc(
        color = color,
        startAngle = rotation,
        sweepAngle = sweepAngle1,
        useCenter = false,
        topLeft = Offset(center.x - radius, center.y - radius),
        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
        style = Stroke(width = strokeWidth.toPx())
    )
    
    drawArc(
        color = color,
        startAngle = rotation + 220f,
        sweepAngle = sweepAngle2,
        useCenter = false,
        topLeft = Offset(center.x - radius, center.y - radius),
        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
        style = Stroke(width = strokeWidth.toPx())
    )
}
