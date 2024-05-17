package com.example.storeroom.ui.categorydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.link.UserLinkInfo
import com.example.storeroom.domain.link.GetLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    val linkItem = MutableStateFlow<List<UserLinkInfo?>>(emptyList())

    init {
        fetchLinksForUser()
    }

    private fun fetchLinksForUser() {
        viewModelScope.launch {
            linkItem.value = getLinkUseCase.getAllLinksFromAllUsers()
        }
    }
}