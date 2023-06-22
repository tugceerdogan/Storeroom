package com.example.storeroom.ui.loginregister.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.register.UserRegister
import com.example.storeroom.domain.register.RegisterUseCase
import com.example.storeroom.util.UIState
import com.google.firebase.FirebaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _userRegister = MutableStateFlow(UserRegister())
    val userRegister: StateFlow<UserRegister> = _userRegister

    private val _uiState = MutableStateFlow<UIState<Boolean>>(UIState.Loading)
    val uiState: StateFlow<UIState<Boolean>> = _uiState


    fun registerUser() {
        if (isEmailValid(_userRegister.value.userEmail)) {
            viewModelScope.launch {
                try {
                    _uiState.value = UIState.Loading
                    val result =registerUseCase(_userRegister.value)
                    _uiState.value = UIState.Success(result)
                } catch (e: FirebaseException) {
                    _uiState.value = UIState.Error(e)
                    //_errorMessage.value = e.localizedMessage
                }

            }
        } else {
            println("Email is not valid")
        }
    }

    fun updateUserName(newUserName: String) {
        _userRegister.value = _userRegister.value.copy(userName = newUserName)
    }

    fun updateUserEmail(newUserEmail: String) {
        _userRegister.value = _userRegister.value.copy(userEmail = newUserEmail)
    }

    fun updateUserPassword(newUserPassword: String) {
        _userRegister.value = _userRegister.value.copy(userPassword = newUserPassword)
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}