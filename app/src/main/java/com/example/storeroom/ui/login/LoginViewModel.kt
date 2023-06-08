package com.example.storeroom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.login.UserLogin
import com.example.storeroom.domain.login.LoginUseCase
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


    fun loginUser() {
        viewModelScope.launch {
            val success = loginUseCase(_userLogin.value)
            if (success) println("Login Success")
            else println("Login failed")
        }
    }

    fun updateUserEmail(newUserEmail: String) {
        _userLogin.value = _userLogin.value.copy(userEmail = newUserEmail)
    }

    fun updateUserPassword(newUserPassword: String) {
        _userLogin.value = _userLogin.value.copy(userPassword = newUserPassword)
    }
}