package com.example.storeroom.ui.login

import androidx.lifecycle.ViewModel
import com.example.storeroom.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginAndRegisterViewModel : ViewModel() {

    private val _user = MutableStateFlow(User())
    val user:StateFlow<User> = _user


    fun updateUserName(newUserName:String){
        _user.value = _user.value.copy(userName = newUserName)
    }

    fun updateUserEmail(newUserEmail:String){
        _user.value = _user.value.copy(userEmail = newUserEmail)
    }

    fun updateUserPassword(newUserPassword:String){
        _user.value = _user.value.copy(userPassword = newUserPassword)
    }
}