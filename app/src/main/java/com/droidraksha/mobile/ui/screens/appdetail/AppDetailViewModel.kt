package com.droidraksha.mobile.ui.screens.appdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidraksha.mobile.data.remote.GroqAgentService
import com.droidraksha.mobile.data.repository.AppRepository
import com.droidraksha.mobile.domain.model.AgentVerdict
import com.droidraksha.mobile.domain.model.AppInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AppDetailUiState(
    val app: AppInfo? = null,
    val isLoading: Boolean = true,
    val agentVerdict: AgentVerdict? = null,
    val isAgentLoading: Boolean = false,
)

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val repository: AppRepository,
    private val groqAgentService: GroqAgentService,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    fun loadApp(packageName: String) {
        repository.getAppByPackage(packageName)
            .onEach { app ->
                _uiState.update { it.copy(app = app, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    fun runAgentVerdict() {
        val app = _uiState.value.app ?: return
        if (_uiState.value.isAgentLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isAgentLoading = true) }
            val verdict = groqAgentService.runAgentVerdict(app)
            _uiState.update { it.copy(agentVerdict = verdict, isAgentLoading = false) }
        }
    }
}

