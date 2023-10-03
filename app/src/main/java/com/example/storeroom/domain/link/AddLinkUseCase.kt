package com.example.storeroom.domain.link

import com.example.storeroom.data.link.UserLinkInfo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class AddLinkUseCase @Inject constructor (private val addLinkRepository: AddLinkRepository) {
    suspend operator fun invoke(userLinkInfo: UserLinkInfo): Task<DocumentReference> {
        return addLinkRepository.addUserLink(userLinkInfo)
    }
}
