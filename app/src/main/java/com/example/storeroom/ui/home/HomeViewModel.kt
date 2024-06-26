package com.example.storeroom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.domain.link.GetLinkUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    val categories = MutableStateFlow<List<String?>>(emptyList())

    init {
        fetchCategoriesForUser()
    }

    fun fetchCategoriesForUser() {
        viewModelScope.launch {
            val userLinkInfo = getLinkUseCase.getAllLinksFromAllUsers()
            val categoryList = userLinkInfo.map { it.category }
            categories.value = categoryList
        }
    }
}
