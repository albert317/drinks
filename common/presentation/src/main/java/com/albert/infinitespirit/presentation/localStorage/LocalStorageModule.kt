package com.albert.infinitespirit.presentation.localStorage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Named("masterKeyAlias")
    @Provides
    @Singleton
    fun provideMasterKeyAlias(): String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context,
        @Named("masterKeyAlias") masterKeyAlias: String
    ): SharedPreferences {

        return try {
            EncryptedSharedPreferences.create(
                LocalStorage.PREF_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (_: Exception) {
            val preferences =
                context.getSharedPreferences(LocalStorage.PREF_NAME, Context.MODE_PRIVATE)
            preferences.edit().apply {
                clear()
                apply()
            }
            EncryptedSharedPreferences.create(
                LocalStorage.PREF_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    @Provides
    @Singleton
    fun provideLocalStorage(preferences: SharedPreferences) = LocalStorage(preferences)

}