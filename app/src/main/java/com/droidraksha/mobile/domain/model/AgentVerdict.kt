package com.droidraksha.mobile.domain.model

data class AgentVerdict(
    val courtNarrative: String = "",
    val iocSummary: String = "",
    val recommendations: List<String> = emptyList(),
    val reasoningSteps: List<String> = emptyList(),
    val verdictConfidence: Int = 0,
    val agentUsed: String = "Groq Llama-3.3-70B",
    val inferenceMs: Long = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
)
