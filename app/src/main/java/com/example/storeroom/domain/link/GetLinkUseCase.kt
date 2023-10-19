package com.example.storeroom.domain.link

import com.example.storeroom.data.link.LinkItem
import javax.inject.Inject

class GetLinkUseCase @Inject constructor(private val getLinkRepository: GetLinkRepository) {
    suspend operator fun invoke(): List<LinkItem> {
        return getLinkRepository.getAllLinksFromAllUsers()
    }
}
