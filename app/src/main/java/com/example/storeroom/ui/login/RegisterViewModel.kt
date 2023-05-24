package com.example.storeroom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.domain.UserRegister
import com.example.storeroom.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userRegister = MutableStateFlow(UserRegister())
    val userRegister:StateFlow<UserRegister> = _userRegister


    fun registerUser(){
        if(isEmailValid(_userRegister.value.userEmail)){
            viewModelScope.launch {
                userRepository.registerUser(_userRegister.value)
            }
        }else{
            println("Email is not valid")
        }
    }

    fun updateUserName(newUserName:String){
        _userRegister.value = _userRegister.value.copy(userName = newUserName)
    }

    fun updateUserEmail(newUserEmail:String){
        _userRegister.value = _userRegister.value.copy(userEmail = newUserEmail)
    }

    fun updateUserPassword(newUserPassword:String){
        _userRegister.value = _userRegister.value.copy(userPassword = newUserPassword)
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}