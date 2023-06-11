package com.example.storeroom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.login.UserLogin
import com.example.storeroom.domain.login.LoginUseCase
import com.example.storeroom.util.UIState
import com.google.firebase.FirebaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _userLogin = MutableStateFlow(UserLogin())
    val userLogin: StateFlow<UserLogin> = _userLogin

    private val _uiState = MutableStateFlow<UIState<Boolean>>(UIState.Loading)
    val uiState: StateFlow<UIState<Boolean>> = _uiState


    fun loginUser() {
        viewModelScope.launch {
            try {
                _uiState.value = UIState.Loading
                val result =loginUseCase(_userLogin.value)
                _uiState.value = UIState.Success(result)
            } catch (e: FirebaseException) {
                _uiState.value = UIState.Error(e)
                //_errorMessage.value = e.localizedMessage
            }
        }
    }

    fun updateUserEmail(newUserEmail: String) {
        _userLogin.value = _userLogin.value.copy(userEmail = newUserEmail)
    }

    fun updateUserPassword(newUserPassword: String) {
        _userLogin.value = _userLogin.value.copy(userPassword = newUserPassword)
    }
}