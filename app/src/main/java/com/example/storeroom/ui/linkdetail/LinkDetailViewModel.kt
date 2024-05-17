package com.example.storeroom.ui.linkdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.link.UserLinkInfo
import com.example.storeroom.domain.link.GetLinkUseCase
import com.example.storeroom.domain.link.UpdateLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkDetailViewModel @Inject constructor(
    val updateLinkUseCase: UpdateLinkUseCase,
    private val getLinkUseCase: GetLinkUseCase
): ViewModel() {

    private val _userLinkInfo = MutableStateFlow(UserLinkInfo())
    val userLinkInfo: StateFlow<UserLinkInfo> = _userLinkInfo

    fun fetchUserLinkInfo(linkId: String) {
        viewModelScope.launch {
            val userLinkInfo = getLinkUseCase.fetchUserLinkInfo(linkId)
            _userLinkInfo.value = userLinkInfo
        }
    }

    fun toggleFavoriteStatus(isFavorite: Boolean, linkId: String) {
        viewModelScope.launch {
            updateLinkUseCase(isFavorite, linkId).addOnSuccessListener {
                _userLinkInfo.value = _userLinkInfo.value.copy(isFavorite = isFavorite)
            }.addOnFailureListener { exception ->
                println(exception)
            }
        }
    }
}
