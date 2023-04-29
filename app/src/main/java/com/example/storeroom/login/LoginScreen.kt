package com.example.storeroom

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun LoginScreen(){
    Column {
        UserEmailTextField()
        UserPasswordTextField()
    }
}

@Composable
fun UserEmailTextField() {
    var userEmail by remember { mutableStateOf("") }

    OutlinedTextField(
        value = userEmail,
        onValueChange = { userEmail = it },
        label = { Text(text = "Email") }
    )
}

@Composable
fun UserPasswordTextField() {
    var userPassword by remember { mutableStateOf("") }

    OutlinedTextField(
        value = userPassword,
        onValueChange = { userPassword = it },
        label = { Text(text = "Password") }
    )
}
