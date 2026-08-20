package com.droidraksha.mobile.ui.screens.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.components.GlassCard
import com.droidraksha.mobile.ui.components.GuardianOrb
import com.droidraksha.mobile.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun LiveScanScreen(
    scanProgress: Int,
    totalToScan: Int,
    currentScanningApp: String,
    isScanning: Boolean,
    onCancel: () -> Unit,
    onScanComplete: () -> Unit // Navigate to results
) {
    // Navigate away when scan finishes
    LaunchedEffect(isScanning) {
        if (!isScanning && scanProgress > 0) {
            onScanComplete()
        }
    }

    val backgroundBrush = Brush.radialGradient(
        colors = listOf(BackgroundSurface, BackgroundDark),
        radius = 1200f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCancel) {
                Icon(Icons.Default.Close, contentDescription = "Cancel Scan", tint = TextPrimary)
            }
            Text("LIVE ANALYSIS", color = TextSecondary, style = Typography.titleMedium)
            com.droidraksha.mobile.ui.components.BrandWordmark()
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Hero Guardian Orb (Scanning State)
        GuardianOrb(size = 320.dp, isScanning = true)

        Spacer(modifier = Modifier.height(80.dp))

        // Status Panel
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = 24.dp
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (currentScanningApp.isNotEmpty()) "Analyzing $currentScanningApp..." else "Analyzing Threat Vectors...",
                    color = AccentCyan,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Progress Bar
                val progress = if (totalToScan > 0) scanProgress.toFloat() / totalToScan else 0f
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = AccentCyan,
                    trackColor = CardLevel2
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Files Scanned", color = TextSecondary, style = Typography.bodyMedium)
                    Text("$scanProgress / $totalToScan", color = TextPrimary, style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = onCancel,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Cancel Scan", color = RiskCritical, style = Typography.titleMedium)
        }
    }
}
