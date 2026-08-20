package com.droidraksha.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidraksha.mobile.domain.model.RiskLevel
import com.droidraksha.mobile.ui.theme.*

@Composable
fun RiskBadge(
    level: RiskLevel,
    score: Int? = null,
    modifier: Modifier = Modifier
) {
    val color = when (level) {
        RiskLevel.CRITICAL -> RiskCritical
        RiskLevel.HIGH -> RiskHigh
        RiskLevel.MEDIUM -> RiskMedium
        RiskLevel.LOW -> RiskLow
        RiskLevel.SAFE -> RiskSafe
    }
    
    val bg = color.copy(alpha = 0.06f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(bg)
            .border(1.dp, color, RoundedCornerShape(100.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        val label = if (score != null) "${level.label} ($score)" else level.label
        Text(
            text = label.uppercase(),
            color = color,
            style = Typography.labelSmall
        )
    }
}

@Composable
fun ThreatChip(
    text: String,
    isWarning: Boolean = true,
    modifier: Modifier = Modifier
) {
    val color = if (isWarning) RiskMedium else AccentCyan
    val bg = color.copy(alpha = 0.06f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(bg)
            .border(1.dp, color, RoundedCornerShape(100.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            color = color,
            style = Typography.labelSmall
        )
    }
}
