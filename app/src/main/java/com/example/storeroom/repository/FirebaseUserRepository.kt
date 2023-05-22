package com.example.storeroom.repository

import com.example.storeroom.domain.UserLogin
import com.example.storeroom.domain.UserRegister
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserRepository @Inject constructor() : UserRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun registerUser(user: UserRegister): Boolean = withContext(Dispatchers.IO) {
        try {
            auth.createUserWithEmailAndPassword(user.userEmail, user.userPassword).await()
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).set(user).await()
                true
            } else false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun loginUser(userLogin: UserLogin): Boolean = withContext(Dispatchers.IO) {
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
