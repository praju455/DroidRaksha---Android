package com.droidraksha.mobile.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.domain.model.AgentVerdict
import com.droidraksha.mobile.ui.theme.*

@Composable
fun AgentVerdictPanel(
    verdict: AgentVerdict?,
    isLoading: Boolean,
    onRunAgent: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var activeTab by remember { mutableStateOf(0) } // 0: Narrative, 1: Reasoning, 2: Recommendations, 3: IOCs

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Expanded Dialog / Card
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Bottom),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 520.dp)
                        .border(1.dp, ShieldCyan.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF6366F1).copy(alpha = 0.2f))
                                        .border(1.dp, Color(0xFF818CF8), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SmartToy,
                                        contentDescription = null,
                                        tint = Color(0xFFA5B4FC),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "LangChain Forensic Verdict",
                                        color = TextPrimary,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = if (verdict != null) "${verdict.agentUsed} • ${verdict.inferenceMs}ms" else "Powered by Groq",
                                        color = TextMuted,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            }

                            IconButton(
                                onClick = { isExpanded = false },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary, modifier = Modifier.size(18.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    CircularProgressIndicator(color = ShieldCyan, modifier = Modifier.size(36.dp))
                                    Text("ReAct Agent Reasoning with Groq LLM...", color = TextSecondary, fontSize = 12.sp)
                                    Text("Synthesizing court-admissible forensic verdict", color = TextMuted, fontSize = 10.sp)
                                }
                            }
                        } else if (verdict != null) {
                            // Confidence & Metric Pill
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(ShieldNavySurface)
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Confidence Score:", color = TextSecondary, fontSize = 12.sp)
                                Text(
                                    text = "${verdict.verdictConfidence}%",
                                    color = if (verdict.verdictConfidence >= 80) RiskLow else RiskHigh,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Sub-navigation Tabs
                            ScrollableTabRow(
                                selectedTabIndex = activeTab,
                                containerColor = Color.Transparent,
                                contentColor = ShieldCyan,
                                edgePadding = 0.dp,
                                divider = {}
                            ) {
                                Tab(
                                    selected = activeTab == 0,
                                    onClick = { activeTab = 0 },
                                    text = { Text("Narrative", fontSize = 12.sp) }
                                )
                                Tab(
                                    selected = activeTab == 1,
                                    onClick = { activeTab = 1 },
                                    text = { Text("Reasoning", fontSize = 12.sp) }
                                )
                                Tab(
                                    selected = activeTab == 2,
                                    onClick = { activeTab = 2 },
                                    text = { Text("Actions", fontSize = 12.sp) }
                                )
                                Tab(
                                    selected = activeTab == 3,
                                    onClick = { activeTab = 3 },
                                    text = { Text("IOCs", fontSize = 12.sp) }
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Content Area
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f, fill = false)
                                    .heightIn(max = 280.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                when (activeTab) {
                                    0 -> item {
                                        Text(
                                            text = verdict.courtNarrative,
                                            color = TextPrimary,
                                            fontSize = 13.sp,
                                            lineHeight = 20.sp
                                        )
                                    }
                                    1 -> items(verdict.reasoningSteps) { step ->
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Text(step, color = TextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
                                        }
                                    }
                                    2 -> items(verdict.recommendations) { rec ->
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = ShieldCyan, modifier = Modifier.size(14.dp))
                                            Text(rec, color = TextPrimary, fontSize = 12.sp, lineHeight = 16.sp)
                                        }
                                    }
                                    3 -> item {
                                        Text(
                                            text = verdict.iocSummary,
                                            color = TextSecondary,
                                            fontSize = 12.sp,
                                            lineHeight = 18.sp
                                        )
                                    }
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text("No agent verdict synthesized yet.", color = TextMuted, fontSize = 12.sp)
                                Button(
                                    onClick = onRunAgent,
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6366F1)),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(Icons.Default.Psychology, contentDescription = null, tint = TextPrimary)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Synthesize Groq Verdict", color = TextPrimary, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            // Floating Pill Trigger Button (Bottom Right)
            Surface(
                onClick = {
                    if (verdict == null && !isLoading) {
                        onRunAgent()
                    }
                    isExpanded = !isExpanded
                },
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFF0F172A),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(if (isExpanded) Color(0xFF818CF8) else ShieldCyan)
                ),
                shadowElevation = 8.dp,
                modifier = Modifier.height(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "Threat Copilot Agent",
                        tint = Color(0xFFA5B4FC),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (isExpanded) "Hide Copilot" else "Agent Verdict",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (isLoading) {
                        CircularProgressIndicator(color = ShieldCyan, modifier = Modifier.size(14.dp), strokeWidth = 2.dp)
                    } else if (verdict != null) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(RiskLow)
                        )
                    }
                }
            }
        }
    }
}
