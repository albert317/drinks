package com.albert.infinitespirit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UI_STATE, EFFECT, INTENT> : ViewModel() {
    private val initialState: UI_STATE by lazy { createInitialState() }
    private val _uiState: MutableStateFlow<UI_STATE> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val intents: MutableSharedFlow<INTENT> = MutableSharedFlow()

    private val _effects = Channel<EFFECT>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        subscribeIntents()
    }

    private fun subscribeIntents() {
        viewModelScope.launch {
            try {
                intents.collect { intent ->
                    handleIntent(intent)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setIntent(intent: INTENT) {
        viewModelScope.launch {
            intents.emit(intent)
        }
    }

    protected fun setUiState(reducer: UI_STATE.() -> UI_STATE) {
        val newState = _uiState.value.reducer()
        _uiState.value = newState
    }

    protected fun setEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    protected abstract fun createInitialState(): UI_STATE

    protected abstract suspend fun handleIntent(intent: INTENT)
}