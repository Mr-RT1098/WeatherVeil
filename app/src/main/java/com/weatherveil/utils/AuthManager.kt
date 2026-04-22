package com.weatherveil.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object AuthManager {

    // ════════════════════════════════════════════════════════
    //  USER CREDENTIALS - CHANGE THESE BEFORE BUILDING
    //
    //  User 1 = YOU
    //  User 2 = YOUR FRIEND
    //
    //  These are stored only in the compiled app binary.
    //  Nobody can see them without decompiling the APK.
    // ════════════════════════════════════════════════════════

    private val USER_1 = UserCredential(
        username = "xxx",
        password = "yyyy",
        displayName = "YO"              // Change to your name
    )

    private val USER_2 = UserCredential(
        username = "XXX",
        password = "YYY",
        displayName = "YO"           // Change to your friend's name
    )

    // ════════════════════════════════════════════════════════

    private const val PREFS_NAME = "secure_session"
    private const val KEY_LOGGED_IN_USER = "logged_in_user"

    data class UserCredential(
        val username: String,
        val password: String,
        val displayName: String
    )

    fun authenticate(username: String, password: String): UserCredential? {
        return when {
            username == USER_1.username && password == USER_1.password -> USER_1
            username == USER_2.username && password == USER_2.password -> USER_2
            else -> null
        }
    }

    fun saveSession(context: Context, username: String) {
        getPrefs(context).edit().putString(KEY_LOGGED_IN_USER, username).apply()
    }

    fun getLoggedInUser(context: Context): String? {
        return getPrefs(context).getString(KEY_LOGGED_IN_USER, null)
    }

    fun clearSession(context: Context) {
        getPrefs(context).edit().remove(KEY_LOGGED_IN_USER).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getLoggedInUser(context) != null
    }

    fun getDisplayName(username: String): String {
        return when (username) {
            USER_1.username -> USER_1.displayName
            USER_2.username -> USER_2.displayName
            else -> username
        }
    }

    private fun getPrefs(context: Context) = try {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } catch (e: Exception) {
        // Fallback to regular prefs if encryption fails
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}
