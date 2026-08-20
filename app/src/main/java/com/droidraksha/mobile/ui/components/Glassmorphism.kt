package com.droidraksha.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.ui.theme.CardLevel1
import com.droidraksha.mobile.ui.theme.CardLevel2
import com.droidraksha.mobile.ui.theme.DividerHairline

@Composable
fun Level1Card(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    contentPadding: Dp = 16.dp,
    borderColor: Color = Color(0x14FFFFFF), // 8% white border default
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(CardLevel1)
            .border(1.dp, borderColor, shape)
            .padding(contentPadding),
        content = content
    )
}

@Composable
fun Level2Card(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    contentPadding: Dp = 16.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(CardLevel2)
            .border(1.dp, Color(0x08FFFFFF), shape) // 3% white border
            .padding(contentPadding),
        content = content
    )
}

// Backward compatibility alias so everything still compiles while we migrate
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    backgroundColor: Color = CardLevel1,
    borderColor: Color = DividerHairline,
    contentPadding: Dp = 16.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Level2Card(modifier, shape, contentPadding, content)
}
