package com.example.storeroom.domain.register

import com.example.storeroom.data.register.UserRegister
import com.google.firebase.FirebaseException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface RegisterRepository {
    suspend operator fun invoke(userRegister: UserRegister): Boolean
}

class RegisterRepositoryImpl @Inject constructor() : RegisterRepository  {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun invoke(userRegister: UserRegister): Boolean = withContext(Dispatchers.IO) {
        try {
            auth.createUserWithEmailAndPassword(userRegister.userEmail, userRegister.userPassword).await()
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).set(userRegister).await()
                true
            } else false
        } catch (e: FirebaseException) {
            e.printStackTrace()
          /*  throw Exception("Registration failed",e)*/
            false
        }
    }
}