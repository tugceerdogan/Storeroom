package com.example.storeroom.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.domain.link.GetLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    val categories = MutableStateFlow<List<String?>>(emptyList())

    init {
        fetchCategoriesForUser()
    }

    fun fetchCategoriesForUser() {
        viewModelScope.launch {
            val user = getLinkUseCase()
            val categoryList = user.map { it.category }
            categories.value = categoryList
        }
    }
}
