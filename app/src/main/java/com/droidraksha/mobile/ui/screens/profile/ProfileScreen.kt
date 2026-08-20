package com.droidraksha.mobile.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.components.GlassCard
import com.droidraksha.mobile.ui.theme.*
import androidx.compose.foundation.border

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BackgroundSurface, BackgroundDark)
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(backgroundBrush),
        topBar = {
            TopAppBar(
                title = { Text("Agent Profile", color = TextPrimary, style = Typography.titleLarge, fontWeight = FontWeight.Bold) },
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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(CardLevel2)
                        .border(2.dp, AccentCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = AccentCyan, modifier = Modifier.size(56.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Jane Doe", color = TextPrimary, style = Typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("jane.doe@droidraksha.gov", color = TextSecondary, style = Typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(RiskSafe.copy(alpha = 0.15f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("Verified Cyber Agent", color = RiskSafe, style = Typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }

            // Options
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                ProfileOptionCard(icon = Icons.Default.Security, title = "Account Security", subtitle = "2FA, Biometrics & Passwords")
                ProfileOptionCard(icon = Icons.Default.Notifications, title = "Notification Preferences", subtitle = "Alert thresholds & push settings")
                
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = RiskCritical.copy(alpha = 0.05f),
                    borderColor = RiskCritical.copy(alpha = 0.2f),
                    contentPadding = 16.dp
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(RiskCritical.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.ExitToApp, contentDescription = null, tint = RiskCritical)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Secure Sign Out", color = RiskCritical, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("Clear session and lock agent profile", color = TextMuted, style = Typography.bodyMedium)
                        }
                        TextButton(onClick = onLogout) {
                            Text("Log Out", color = RiskCritical, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileOptionCard(icon: ImageVector, title: String, subtitle: String) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 16.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardLevel2),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = AccentCyan)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = TextPrimary, style = Typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(subtitle, color = TextSecondary, style = Typography.bodyMedium)
            }
        }
    }
}
