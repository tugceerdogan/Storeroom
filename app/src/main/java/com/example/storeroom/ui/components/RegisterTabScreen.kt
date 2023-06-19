package com.example.storeroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen
import com.example.storeroom.ui.register.RegisterViewModel
import com.example.storeroom.util.UIState

@Composable
fun RegisterTabScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val user by registerViewModel.userRegister.collectAsState()

    val userNameField = remember { mutableStateOf(TextFieldValue(user.userName)) }
    val userEmailField = remember { mutableStateOf(TextFieldValue(user.userEmail)) }
    val userPasswordField = remember { mutableStateOf(TextFieldValue(user.userPassword)) }

    var snackbarVisibleState by remember { mutableStateOf(false) }

    val uiState by registerViewModel.uiState.collectAsState()

    when (uiState) {
        is UIState.Loading -> {
        }
        is UIState.Success -> {
            navHostController.navigate(Screen.Login.route)
        }
        is UIState.Error -> {
            val scaffoldState = rememberScaffoldState()
            LaunchedEffect(key1 = true) {
                scaffoldState.snackbarHostState.showSnackbar(
                    (uiState as UIState.Error).exception.message ?: "An error occurred"
                )
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInputField(
            value = userNameField.value,
            label = "Name",
            onValueChange = { newValue ->
                userNameField.value = newValue
                registerViewModel.updateUserName(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInputField(
            value = userEmailField.value,
            label = "Email Address",
            onValueChange = { newValue ->
                userEmailField.value = newValue
                registerViewModel.updateUserEmail(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInputField(
            value = userPasswordField.value,
            label = "Password",
            onValueChange = { newValue ->
                userPasswordField.value = newValue
                registerViewModel.updateUserPassword(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(90.dp))

        UserButton(
            onClick = {
                println("User Name:  ${user.userName}")
                println("Email Address: ${user.userEmail}")
                println("Password : ${user.userPassword}")
                snackbarVisibleState = true
                registerViewModel.registerUser()
            },
            text = "Register"
        )
    }
}
