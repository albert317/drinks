package com.albert.infinitespirit.firebase.di

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GoogleAuthModule {

    @Provides
    @Singleton
    fun provideGoogleSingClient(application: Application): GoogleSignInClient {
        val options=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("990974668128-a0dntao1kmtgf6to1v8016ond20gadk4.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(application, options)
    }

}