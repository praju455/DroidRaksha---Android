package com.droidraksha.mobile.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.ui.components.GlassCard
import com.droidraksha.mobile.ui.components.GuardianOrb
import com.droidraksha.mobile.ui.components.BrandWordmark
import com.droidraksha.mobile.ui.theme.*

@Composable
fun OnboardingScreen(
    onAcceptAndContinue: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Premium radial gradient background for the whole screen
    val backgroundBrush = Brush.radialGradient(
        colors = listOf(BackgroundSurface, BackgroundDark),
        radius = 1500f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hero Section with Guardian Orb
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) {
            GuardianOrb(size = 280.dp, isScanning = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        BrandWordmark()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your Android,\nGuarded.",
            color = TextPrimary,
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        Text(
            text = "On-Device Threat Intelligence against malware, network attacks, and privacy abuse.",
            color = TextSecondary,
            style = Typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Privacy & Consent Card
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column {
                Text(
                    text = "🔒 Privacy & Transparency Guarantee",
                    color = AccentCyan,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "DroidRaksha operates entirely on-device. Your photos, private messages, personal contacts, and browsing history are never uploaded or inspected.",
                    color = TextSecondary,
                    style = Typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Required Permissions Breakdown
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            PermissionItem(
                icon = Icons.Default.Apps,
                title = "App Inventory Analysis",
                description = "Scans installed app signatures to identify trojans and fake apps."
            )
            Spacer(modifier = Modifier.height(16.dp))
            PermissionItem(
                icon = Icons.Default.NetworkCheck,
                title = "Network Visibility",
                description = "Monitors background network patterns to detect C2 bot beaconing."
            )
            Spacer(modifier = Modifier.height(16.dp))
            PermissionItem(
                icon = Icons.Default.Psychology,
                title = "On-Device AI Engine",
                description = "Executes local Machine Learning models offline with zero battery drain."
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // CTA Button
        val buttonGradient = Brush.horizontalGradient(
            colors = listOf(AccentCyan, AccentCyan, AccentCyan)
        )
        
        Button(
            onClick = onAcceptAndContinue,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(buttonGradient),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Grant Consent & Start Scan",
                    color = Color.White,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun PermissionItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(CardLevel2),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AccentCyan,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title, 
                color = TextPrimary, 
                style = Typography.titleMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description, 
                color = TextMuted, 
                style = Typography.bodyMedium
            )
        }
    }
}
