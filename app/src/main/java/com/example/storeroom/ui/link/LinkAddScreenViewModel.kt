package com.example.storeroom.ui.link

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.link.UserLinkInfo
import com.example.storeroom.domain.link.AddLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkAddScreenViewModel @Inject constructor(
    private val addLinkUseCase: AddLinkUseCase
) : ViewModel() {

    private val _userLinkInfo = MutableStateFlow(UserLinkInfo())
    val userLinkInfo: StateFlow<UserLinkInfo> = _userLinkInfo

    private val _addLinkResult = MutableStateFlow(Result.success(Unit))

    fun addLinkToUser() {
        viewModelScope.launch {
            addLinkUseCase(_userLinkInfo.value)
                .addOnSuccessListener {
                    _addLinkResult.value = Result.success(Unit)
                }
                .addOnFailureListener { exception ->
                    _addLinkResult.value = Result.failure(exception)
                }
        }
    }

    fun updateUrl(newUrl: String) {
        _userLinkInfo.value = _userLinkInfo.value.copy(url = newUrl)
    }

    fun updateCategory(newCategory: String) {
        _userLinkInfo.value = _userLinkInfo.value.copy(category = newCategory)
    }
}
