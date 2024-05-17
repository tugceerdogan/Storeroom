package com.example.storeroom.ui

import androidx.lifecycle.ViewModel
import com.example.storeroom.data.link.UserLinkInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedLinkItem = MutableStateFlow<UserLinkInfo?>(null)
    val selectedLinkItem: StateFlow<UserLinkInfo?> = _selectedLinkItem

    fun selectLinkItem(item: UserLinkInfo?) {
        _selectedLinkItem.value = item
    }
}
