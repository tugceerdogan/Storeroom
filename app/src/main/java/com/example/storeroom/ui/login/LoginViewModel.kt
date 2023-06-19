package com.example.storeroom.ui.login

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeroom.data.login.UserLogin
import com.example.storeroom.domain.auth.GoogleSignInUseCase
import com.example.storeroom.domain.login.LoginUseCase
import com.example.storeroom.util.UIState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val useCase: GoogleSignInUseCase
) : ViewModel() {

    private val _userLogin = MutableStateFlow(UserLogin())
    val userLogin: StateFlow<UserLogin> = _userLogin

    private val _uiState = MutableStateFlow<UIState<Boolean>>(UIState.Loading)
    val uiState: StateFlow<UIState<Boolean>> = _uiState

    private val _user = MutableLiveData<FirebaseUser?>(null)
    val user: LiveData<FirebaseUser?> = _user

    fun loginUser() {
        viewModelScope.launch {
            try {
                val result = loginUseCase(_userLogin.value)
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

    fun signInWithGoogle(data: Intent?) {
        useCase.execute(data)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = Firebase.auth.currentUser
                } else {
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
}