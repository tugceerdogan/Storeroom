package com.example.storeroom.domain.link

import com.example.storeroom.data.link.UserLinkInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface GetLinkRepository {
    suspend fun getAllLinksFromAllUsers(): List<UserLinkInfo>

    suspend fun fetchUserLinkInfo(linkId: String): UserLinkInfo
}
class GetLinkRepositoryImpl @Inject constructor() : GetLinkRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override suspend fun getAllLinksFromAllUsers(): List<UserLinkInfo> {
        return try {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val linkCollection = db.collection("users").document(currentUser.uid).collection("link")
                val querySnapshot = linkCollection.get().await()

                querySnapshot.documents.mapNotNull { document ->
                    document.toObject(UserLinkInfo::class.java)?.copy(linkId = document.id)
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun fetchUserLinkInfo(linkId: String): UserLinkInfo {
        val userLinkRef = db.collection("users")
            .document(auth.uid.orEmpty())
            .collection("link")
            .document(linkId)

        val document = userLinkRef.get().await()
        return document.toObject(UserLinkInfo::class.java)?.copy(linkId = document.id) ?: UserLinkInfo()
    }
}
