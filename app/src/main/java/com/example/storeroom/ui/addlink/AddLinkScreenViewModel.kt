package com.example.storeroom.ui.addlink

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.link.UserLinkInfo
import com.example.storeroom.domain.link.AddLinkUseCase
import com.example.storeroom.domain.link.GetLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AddLinkScreenViewModel @Inject constructor(
    private val addLinkUseCase: AddLinkUseCase,
    private val getLinkUseCase: GetLinkUseCase,
) : ViewModel() {

    private val _userLinkInfo = MutableStateFlow(UserLinkInfo())
    val userLinkInfo: StateFlow<UserLinkInfo> = _userLinkInfo

    private val _addLinkResult = MutableStateFlow(Result.success(Unit))
    val addLinkResult: StateFlow<Result<Unit>> = _addLinkResult

    val categories = MutableStateFlow<List<String?>>(emptyList())

    init {
        fetchCategories()
    }

    fun addLinkToUser() {
        viewModelScope.launch {
            try {
                val documentReference = addLinkUseCase(_userLinkInfo.value).await()
                val linkId = documentReference.id
                _userLinkInfo.value = _userLinkInfo.value.copy(linkId = linkId)
                _addLinkResult.value = Result.success(Unit)
            } catch (exception: Exception) {
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

    fun updateNote(newNote: String) {
        _userLinkInfo.value = _userLinkInfo.value.copy(note = newNote)
    }

    fun isValidLink(link: String): Boolean {
        val pattern = Patterns.WEB_URL
        return pattern.matcher(link).matches()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val categoryList = getLinkUseCase.getAllLinksFromAllUsers().map {
                it.category
            }
            categories.value = categoryList
        }
    }
}
