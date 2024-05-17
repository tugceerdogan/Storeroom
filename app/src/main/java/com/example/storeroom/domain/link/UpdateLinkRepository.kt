package com.example.storeroom.domain.link

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface UpdateLinkRepository {
    suspend fun updateLink(
        isFavorite: Boolean,
        linkId: String,
    ): Task<Void>
}

class UpdateLinkRepositoryImpl @Inject constructor() : UpdateLinkRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun updateLink(
        isFavorite: Boolean,
        linkId: String
    ): Task<Void> {
        val userLinkRef = db.collection("users")
            .document(auth.uid.orEmpty())
            .collection("link")
            .document(linkId)

        return userLinkRef.update("isFavorite", isFavorite)
    }
}
