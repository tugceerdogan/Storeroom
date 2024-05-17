package com.example.storeroom.domain.link

import com.example.storeroom.data.link.UserLinkInfo
import javax.inject.Inject

class GetLinkUseCase @Inject constructor(private val getLinkRepository: GetLinkRepository) {
    suspend fun getAllLinksFromAllUsers(): List<UserLinkInfo> {
        return getLinkRepository.getAllLinksFromAllUsers()
    }

    suspend fun fetchUserLinkInfo(linkId: String): UserLinkInfo {
        return getLinkRepository.fetchUserLinkInfo(linkId)
    }
}
