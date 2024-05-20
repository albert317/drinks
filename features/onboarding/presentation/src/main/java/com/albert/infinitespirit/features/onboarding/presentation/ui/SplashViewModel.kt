package com.albert.infinitespirit.features.onboarding.presentation.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.common.domain.Failure
import com.albert.infinitespirit.presentation.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val application: Application

) :
    BaseViewModel<SplashUiState, SplashEvent, SplashIntent, SplashNavigationModel>() {
    override fun createInitialState(): SplashUiState {
        return SplashUiState()
    }

    override suspend fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.getVersion -> {}
            is SplashIntent.hasSession -> {}
        }
    }

    private fun getVersion() {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                // get version

            } catch (e: Exception) {
                setUiState {
                    copy(
                        isLoading = false,
                        error = Failure.CustomError(e.message.toString())
                    )
                }
                setEffect(SplashEvent.showAlert(Failure.CustomError(e.message ?: "Unknown Error")))
            }
        }
    }

    fun hasGoogleSession(): Boolean {
        //return firebaseAuth.currentUser != null
        val googleAccount = GoogleSignIn.getLastSignedInAccount(application)
        return googleAccount != null
    }

}