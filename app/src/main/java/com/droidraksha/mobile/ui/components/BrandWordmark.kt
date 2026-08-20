package com.droidraksha.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.theme.OrbCyan
import com.droidraksha.mobile.ui.theme.OrbBlue
import com.droidraksha.mobile.ui.theme.TextPrimary
import com.droidraksha.mobile.ui.theme.Typography

@Composable
fun BrandWordmark(modifier: Modifier = Modifier, showIcon: Boolean = true) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        if (showIcon) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(OrbCyan, OrbBlue)
                        )
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        val brandText = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                append("Droid")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Raksha")
            }
        }

        Text(
            text = brandText,
            color = TextPrimary,
            style = Typography.titleLarge,
            letterSpacing = Typography.titleLarge.letterSpacing
        )
    }
}
