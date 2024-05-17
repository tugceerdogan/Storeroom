package com.example.storeroom.domain.link

import com.google.android.gms.tasks.Task
import javax.inject.Inject

class UpdateLinkUseCase @Inject constructor(
    private val updateLinkRepository: UpdateLinkRepository
) {
    suspend operator fun invoke(
        isFavorite: Boolean,
        linkId: String,
    ): Task<Void> {
        return updateLinkRepository.updateLink(isFavorite, linkId)
    }
}
