package com.example.storeroom.domain.auth

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class UserSessionManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun setLoggedInStatus(loggedIn: Boolean) {
        Log.d("LOGIN_STATUS", "Setting logged in status to: $loggedIn")
        sharedPreferences.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }
}
