package com.example.storeroom.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.storeroom.R
import com.example.storeroom.nav.Screen
import com.example.storeroom.ui.login.LoginViewModel
import com.example.storeroom.ui.register.RegisterViewModel
import com.example.storeroom.util.StoreroomTheme.customBoldFont
import com.example.storeroom.util.StoreroomTheme.editTextBackgroundColor
import com.example.storeroom.util.StoreroomTheme.rememberPasswordTextColor
import com.example.storeroom.util.StoreroomTheme.termAndPolicyClickableTextColor
import com.example.storeroom.util.StoreroomTheme.termAndPolicyTextColor
import com.example.storeroom.util.StoreroomTheme.unselectTabColor
import com.example.storeroom.util.StoreroomTheme.whiteTextStyle
import com.example.storeroom.util.UIState

@Composable
fun LoginAndRegisterScreen(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val snackbarVisibleState = remember { mutableStateOf(false) }
        TitleText()
        TermAndPolicyText(
            onClick = {},
            text = "By signing in you are agreeing our ",
            clickableText = "Term and privacy policy"
        )
        Spacer(modifier = Modifier.height(45.dp))
        LoginOrRegisterTabLayout(loginViewModel, registerViewModel, navHostController)
        LoginSuccesfulSnackbar(
            snackbarVisibleState,
            "Login Successful"
        )
    }
}

@Composable
fun TitleText() {
    Text(
        text = "Welcome",
        style = MaterialTheme.typography.body1.copy(
            fontSize = 40.sp,
            fontFamily = customBoldFont
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TermAndPolicyText(
    text: String,
    clickableText: String,
    onClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = termAndPolicyTextColor,
            )
        ) {
            append(text)
        }
        withStyle(
            style = SpanStyle(
                color = termAndPolicyClickableTextColor,
                textDecoration = TextDecoration.None,
            )
        ) {
            append(clickableText)
            addStringAnnotation(
                tag = "Clickable",
                annotation = "",
                start = length - clickableText.length,
                end = length
            )
        }
    }
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "Clickable",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onClick()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    )
}

@Composable
fun LoginOrRegisterTabLayout(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    navHostController: NavHostController
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.White,
            contentColor = unselectTabColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Black,
                    modifier = Modifier
                        .width(10.dp)
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            ) {
                Text(text = "Login")
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            ) {
                Text(text = "Register")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (selectedTabIndex == 0) {
                LoginTabScreen(loginViewModel, navHostController)
            } else {
                RegisterTabScreen(registerViewModel, navHostController)
            }
        }
    }
}

@Composable
fun LoginSuccesfulSnackbar(
    snackbarVisibleState: MutableState<Boolean>,
    snackbarText: String
) {
    if (snackbarVisibleState.value) {
        Snackbar(
            action = {
                TextButton(onClick = { snackbarVisibleState.value = false }) {
                    Text(text = "OK", style = whiteTextStyle)
                }
            },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(text = snackbarText)
        }
    }
}

@Composable
fun LoginTabScreen(
    loginViewModel: LoginViewModel,
    navHostController: NavHostController,
) {
    val user by loginViewModel.userLogin.collectAsState()
    val userEmailField = remember { mutableStateOf(TextFieldValue(user.userEmail)) }
    val userPasswordField = remember { mutableStateOf(TextFieldValue(user.userPassword)) }
    var snackbarVisibleState by remember { mutableStateOf(false) }

    val uiState by loginViewModel.uiState.collectAsState()

    when (uiState) {
        is UIState.Loading -> {
        }
        is UIState.Success -> {
            if ((uiState as UIState.Success).data) {
                navHostController.navigate(Screen.Home.route)
            } else {
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(key1 = true) {
                    scaffoldState.snackbarHostState.showSnackbar("Login failed")
                }
            }
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
                println("Email Address: ${user.userEmail}")
                println("Password : ${user.userPassword}")
                snackbarVisibleState = true
                loginViewModel.loginUser()
            }
        )
    }
}

@Composable
fun RegisterTabScreen(
    registerViewModel: RegisterViewModel,
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

@Composable
fun UserInputField(
    value: TextFieldValue,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color(0xFF928A9C)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = editTextBackgroundColor,
            textColor = Color.Black,
            focusedBorderColor = Color(0xFF928A9C),
            unfocusedBorderColor = Color(0xFFCCC9C9)
        ),
        shape = RoundedCornerShape(16.dp),
    )
}

@Composable
fun UserButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = termAndPolicyClickableTextColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text, style = whiteTextStyle)
    }
    Text(
        text = "or connect with",
        color = rememberPasswordTextColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        textAlign = TextAlign.Center
    )
    SocialMediaImages()
}

@Composable
fun PasswordRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Checkbox(checked = false, onCheckedChange = { })
        Text(
            text = "Remember Password",
            color = rememberPasswordTextColor
        )
        Text(
            text = "Forget Password",
            modifier = Modifier.padding(start = 58.dp),
            color = termAndPolicyClickableTextColor
        )
    }
}


@Composable
fun SocialMediaImages() {
    val facebookIcon = painterResource(R.drawable.facebook_icon)
    val googleIcon = painterResource(R.drawable.google_icon)
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = facebookIcon,
            contentDescription = "Facebook",
            modifier = Modifier
                .size(50.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = googleIcon,
            contentDescription = "Google",
            modifier = Modifier
                .size(50.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLoginAndRegisterScreen() {
}