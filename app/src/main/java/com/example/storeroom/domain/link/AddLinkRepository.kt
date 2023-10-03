package com.example.storeroom.domain.link

import com.example.storeroom.data.link.UserLinkInfo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface AddLinkRepository {
    suspend fun addUserLink(userLinkInfo: UserLinkInfo): Task<DocumentReference>
}
class AddLinkRepositoryImpl @Inject constructor() : AddLinkRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun addUserLink(userLinkInfo: UserLinkInfo): Task<DocumentReference> {
        val userLinksRef = db.collection("users").document(auth.uid.orEmpty()).collection("link")
        return userLinksRef.add(userLinkInfo)
    }
}
