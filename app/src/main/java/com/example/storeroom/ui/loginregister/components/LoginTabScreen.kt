package com.example.storeroom.ui.loginregister.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen
import com.example.storeroom.ui.loginregister.login.LoginViewModel
import com.example.storeroom.util.CustomLoading
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.UIState
import kotlinx.coroutines.launch

@Composable
fun LoginTabScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val userLoginState by loginViewModel.userLogin.collectAsStateWithLifecycle()
    val userState by loginViewModel.user.observeAsState()

    val userEmailField = remember { mutableStateOf(TextFieldValue(userLoginState.userEmail)) }
    val userPasswordField = remember { mutableStateOf(TextFieldValue(userLoginState.userPassword)) }

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val isLoggedIn by loginViewModel.isLoggedIn.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        loginViewModel.checkUserLoggedInStatus()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UserInputTextField(
            value = userEmailField.value,
            label = "Email Address",
            onValueChange = { newValue ->
                userEmailField.value = newValue
                loginViewModel.updateUserEmail(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserPasswordInputField(
            value = userPasswordField.value,
            label = "Password",
            onValueChange = { newValue ->
                userPasswordField.value = newValue
                loginViewModel.updateUserPassword(newValue.text)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        PasswordRow()

        Spacer(modifier = Modifier.height(60.dp))

        UserButton(
            text = "Login",
            onClick = {
                loginViewModel.loginUser()
            }
        )

        Text(
            text = "or connect with",
            color = StoreroomColor.storeRoomBrown,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            textAlign = TextAlign.Center
        )

        GoogleSignInButton(
            viewModel = loginViewModel,
            onSignedIn = { navHostController.navigate(Screen.Home.route) },
            onError = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("An error occurred")
                }
            }
        )
    }

    when (uiState) {
        is UIState.Loading -> {
        }
        is UIState.Success -> {
            val successData = (uiState as UIState.Success).data
            if (successData) {
                CustomLoading()
                navHostController.navigate(Screen.Home.route)
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Login successful")
                }
            } else {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Login failed")
                }
            }
        }
        is UIState.Error -> {
            val errorException = (uiState as UIState.Error).exception

            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    errorException.message ?: "An error occurred"
                )
            }
        }
    }

    if (isLoggedIn) {
        navHostController.navigate(Screen.Home.route)
    } else {
    }
}
