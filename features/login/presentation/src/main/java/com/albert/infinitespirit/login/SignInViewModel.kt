package com.albert.infinitespirit.login

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.infinitespirit.presentation.localStorage.LocalStorage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val firebaseAuth: FirebaseAuth,
    private val application: Application,
    private val localStorage: LocalStorage
) : ViewModel() {

    private fun signInWithFirebase(credential: AuthCredential, navigation: () -> Unit) =
        viewModelScope.launch {
            try {
                firebaseAuth.signInWithCredential(credential).await()
                navigation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    fun getGoogleAuthCredential(intent: Intent?, navigation: () -> Unit) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        val account = task.getResult(ApiException::class.java)
        val example = account.photoUrl?.toString()
        saveAccount(account)
        Log.d("photoUrl", "photoUrl: $example")

        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        signInWithFirebase(credential, navigation)
    }


    fun signInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun signOut() {
        googleSignInClient.signOut()
        firebaseAuth.signOut()
    }

    fun removeAccess() {
        googleSignInClient.revokeAccess()
        val user = firebaseAuth.currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "User account deleted.")
            }
        }
    }

    fun hasGoogleSession(): Boolean {
        //return firebaseAuth.currentUser != null
        val googleAccount = GoogleSignIn.getLastSignedInAccount(application)
        return googleAccount != null
    }

    private fun saveAccount(account: GoogleSignInAccount) {
        localStorage.putString(LocalStorage.ACCOUNT_EMAIL, account.email)
        localStorage.putString(LocalStorage.ACCOUNT_NAME, account.displayName)
        localStorage.putString(LocalStorage.ACCOUNT_PHOTO, account.photoUrl?.toString())
    }
}