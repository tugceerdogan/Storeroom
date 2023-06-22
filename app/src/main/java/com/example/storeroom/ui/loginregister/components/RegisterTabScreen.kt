package com.example.storeroom.ui.loginregister.components

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
import com.example.storeroom.ui.loginregister.register.RegisterViewModel
import com.example.storeroom.util.UIState
import kotlinx.coroutines.launch

@Composable
fun RegisterTabScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val user by registerViewModel.userRegister.collectAsState()

    val userNameField = remember { mutableStateOf(TextFieldValue(user.userName)) }
    val userEmailField = remember { mutableStateOf(TextFieldValue(user.userEmail)) }
    val userPasswordField = remember { mutableStateOf(TextFieldValue(user.userPassword)) }


    val uiState by registerViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    when (uiState) {
        is UIState.Loading -> {
        }
        is UIState.Success -> {
            navHostController.navigate(Screen.Login.route)
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Register successful")
            }
        }
        is UIState.Error -> {
            val errorException = (uiState as UIState.Error).exception
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(errorException.message ?: "An error occurred")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInputTextField(
            value = userNameField.value,
            label = "Name",
            onValueChange = { newValue ->
                userNameField.value = newValue
                registerViewModel.updateUserName(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInputTextField(
            value = userEmailField.value,
            label = "Email Address",
            onValueChange = { newValue ->
                userEmailField.value = newValue
                registerViewModel.updateUserEmail(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserPasswordInputField(
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
                registerViewModel.registerUser()
            },
            text = "Register"
        )
    }
}
