package com.example.storeroom.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.domain.link.GetLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryStaggeredListViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    val categories = MutableStateFlow<List<String?>>(emptyList())

    init {
        fetchCategoriesForStaggeredList()
    }

    fun fetchCategoriesForStaggeredList() {
        viewModelScope.launch {
            val userLinkInfo = getLinkUseCase()
            val categoryList = userLinkInfo.map { it.category }
            categories.value = categoryList
        }
    }
}
