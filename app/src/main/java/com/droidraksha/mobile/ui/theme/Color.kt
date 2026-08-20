package com.droidraksha.mobile.ui.theme

import androidx.compose.ui.graphics.Color

// NeonShield Inspired Cybersecurity Deep Black Palette
val BackgroundDark = Color(0xFF02040A)
val BackgroundSurface = Color(0xFF000000)

// Elevation & Hierarchy (Flat, matte cards)
val CardLevel1 = Color(0xFF111111) // Primary/Hero cards
val CardLevel2 = Color(0xFF111111) // Standard content cards
val DividerHairline = Color(0x14FFFFFF) // 8% white for borders/dividers

// Single Brand Accent
val AccentCyan = Color(0xFF00F0FF)
val AccentGlow = Color(0xFF1A56FF)

// Strict Semantic Risk System (Collapsed from 5-tier to 3-tier colors)
val RiskCritical = Color(0xFFFF4757) // Red
val RiskMedium = Color(0xFFFFB020)   // Amber
val RiskSafe = Color(0xFF00D68A)     // Green

// Backward compatibility mappings for existing 5-Tier Enum logic
val RiskHigh = RiskCritical // Use Critical red
val RiskLow = RiskMedium    // Use Medium amber

// Text colors (High contrast)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF8B949E)
val TextMuted = Color(0xFF8B949E)

// Status & Special
val IndiaFlagSaffron = Color(0xFFFF9933)
val IndiaFlagGreen = Color(0xFF138808)
val C2BeaconAlert = RiskCritical

// GuardianOrb component ONLY
val OrbCyan = Color(0xFF00F0FF)
val OrbBlue = Color(0xFF1A56FF)
