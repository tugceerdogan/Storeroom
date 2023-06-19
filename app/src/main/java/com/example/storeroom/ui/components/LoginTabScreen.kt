package com.example.storeroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen
import com.example.storeroom.ui.login.LoginViewModel
import com.example.storeroom.util.StoreroomTheme
import com.example.storeroom.util.UIState

@Composable
fun LoginTabScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val userLoginState by loginViewModel.userLogin.collectAsState()
    val userState by loginViewModel.user.observeAsState()

    val userEmailField = remember { mutableStateOf(TextFieldValue(userLoginState.userEmail)) }
    val userPasswordField = remember { mutableStateOf(TextFieldValue(userLoginState.userPassword)) }

    val uiState by loginViewModel.uiState.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UserInputField(
            value = userEmailField.value,
            label = "Email Address",
            onValueChange = { newValue ->
                userEmailField.value = newValue
                loginViewModel.updateUserEmail(newValue.text)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInputField(
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
            color = StoreroomTheme.rememberPasswordTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            textAlign = TextAlign.Center
        )

        GoogleSignInButton(
            viewModel = loginViewModel,
            onSignedIn = { navHostController.navigate(Screen.Home.route) },
            onError = {}
        )
    }

    when (uiState) {
        is UIState.Loading -> {
        }
        is UIState.Success -> {
            val successData = (uiState as UIState.Success).data
            if (successData) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            color = Color.Red,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading...")
                    }
                }
                navHostController.navigate(Screen.Home.route)
            } else {
                LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar("Login failed")
                }
            }
        }
        is UIState.Error -> {
            val errorException = (uiState as UIState.Error).exception

            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    errorException.message ?: "An error occurred"
                )
            }
        }
    }
}
