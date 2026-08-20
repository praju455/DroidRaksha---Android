package com.droidraksha.mobile.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AccentCyan,
    onPrimary = BackgroundDark,
    primaryContainer = CardLevel2,
    onPrimaryContainer = AccentCyan,
    secondary = AccentCyan,
    onSecondary = TextPrimary,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = BackgroundSurface,
    onSurface = TextPrimary,
    surfaceVariant = CardLevel2,
    onSurfaceVariant = TextSecondary,
    outline = DividerHairline,
    error = RiskCritical,
    onError = TextPrimary,
)

@Composable
fun DroidRakshaTheme(
    darkTheme: Boolean = true, // Force Dark Mode as per request
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BackgroundDark.toArgb()
            window.navigationBarColor = BackgroundDark.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
