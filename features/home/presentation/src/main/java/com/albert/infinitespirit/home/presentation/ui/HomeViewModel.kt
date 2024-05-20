package com.albert.infinitespirit.home.presentation.ui

import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.presentation.BaseViewModel
import com.albert.infinitespirit.presentation.localStorage.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localStorage: LocalStorage
) :
    BaseViewModel<HomeUiState, HomeEvent, HomeIntent, HomeNavigationModel>() {

    override fun createInitialState(): HomeUiState {
        return HomeUiState()
    }

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadDataUser -> loadDataUser()
            is HomeIntent.GoToSaved -> {}
            is HomeIntent.GoToLogOut -> {}
            is HomeIntent.GoToSettings -> {}
        }
    }

    private fun loadDataUser() {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val userName = localStorage.getString(LocalStorage.ACCOUNT_NAME) ?: ""
                val email = localStorage.getString(LocalStorage.ACCOUNT_EMAIL) ?: ""
                val photoUrl = localStorage.getString(LocalStorage.ACCOUNT_PHOTO) ?: ""
                setUiState {
                    copy(
                        userName = userName,
                        email = email,
                        photoUrl = photoUrl,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                setUiState { copy(isLoading = false, error = Throwable(e.message)) }
                setEffect(
                    HomeEvent.showAlert(
                        Failure.CustomError(e.message ?: "Unknown Error")
                    )
                )
            }
        }
    }
}