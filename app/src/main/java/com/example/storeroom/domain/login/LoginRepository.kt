package com.example.storeroom.domain.login

import com.example.storeroom.data.login.UserLogin
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginRepository {
    suspend operator fun invoke(userLogin: UserLogin): Boolean
}
class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    private val auth = Firebase.auth

    override suspend fun invoke(userLogin: UserLogin): Boolean  = withContext(Dispatchers.IO) {
        try {
            auth.signInWithEmailAndPassword(userLogin.userEmail,userLogin.userPassword).await()
            val firebaseUser = auth.currentUser
            firebaseUser != null
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
