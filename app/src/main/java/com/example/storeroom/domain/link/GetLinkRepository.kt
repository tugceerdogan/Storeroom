package com.example.storeroom.domain.link

import com.example.storeroom.data.link.LinkItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface GetLinkRepository {
    suspend fun getAllLinksFromAllUsers(): List<LinkItem>
}
class GetLinkRepositoryImpl @Inject constructor() : GetLinkRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override suspend fun getAllLinksFromAllUsers(): List<LinkItem> {
        return try {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val linkCollection = db.collection("users").document(currentUser.uid).collection("link")
                val querySnapshot = linkCollection.get().await()

                querySnapshot.documents.mapNotNull { document ->
                    document.toObject(LinkItem::class.java)
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
