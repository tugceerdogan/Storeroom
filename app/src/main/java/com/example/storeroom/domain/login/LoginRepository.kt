package com.example.storeroom.domain.login

import com.example.storeroom.data.login.UserLogin
import com.example.storeroom.domain.auth.UserSessionManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginRepository {
    suspend operator fun invoke(userLogin: UserLogin): Boolean
    suspend fun getLoggedInStatus(): Boolean
}

class LoginRepositoryImpl @Inject constructor(
    private val userSessionManager: UserSessionManager
) : LoginRepository {
    private val auth = Firebase.auth

    override suspend fun invoke(userLogin: UserLogin): Boolean = withContext(Dispatchers.IO) {
        try {
            auth.signInWithEmailAndPassword(userLogin.userEmail, userLogin.userPassword).await()
            val firebaseUser = auth.currentUser
            val isLoggedIn = firebaseUser != null

            if (isLoggedIn) {
                userSessionManager.setLoggedInStatus(true)
            }

            return@withContext isLoggedIn
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }

    override suspend fun getLoggedInStatus(): Boolean {
        return userSessionManager.isLoggedIn()
    }
}
