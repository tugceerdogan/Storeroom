package com.example.storeroom.ui.login


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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.storeroom.R

val whiteTextStyle = TextStyle(color = Color.White)
val customBoldFont = FontFamily(Font(R.font.majallabold))
val termAndPolicyTextColor = Color(0xFF6B5E5E)
val termAndPolicyClickableTextColor = Color(0xFF0386D0)
val unselectTabColor = Color(0xFFA6A6A6)
val rememberPasswordTextColor = Color(0xFF6B5E5E)
val editTextBackgroundColor = Color(0xFFF9F9F9)

@Composable
fun LoginAndRegisterScreen() {
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
        LoginOrRegisterTabLayout()
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
fun LoginOrRegisterTabLayout() {
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
                LoginTabScreen()
            } else {
                RegisterTabScreen()
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
fun LoginTabScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val userEmail = remember { mutableStateOf(TextFieldValue()) }
        val userPassword = remember { mutableStateOf(TextFieldValue()) }
        var snackbarVisibleState by remember { mutableStateOf(false) }

        //UserInputField(userEmail.value.text, "Email Address")
        Spacer(modifier = Modifier.height(10.dp))

        //UserInputField(userPassword.value.text, "Password")
        Spacer(modifier = Modifier.height(10.dp))

        PasswordRow()
        Spacer(modifier = Modifier.height(60.dp))

        UserButton(onClick = {
            val userEmailText = userEmail.value.text
            val userPasswordText = userPassword.value.text
            println("Email Address: $userEmailText")
            println("Password : $userPasswordText")
            snackbarVisibleState = true
        }, text = "Login")
    }
}

@Composable
fun RegisterTabScreen(registerViewModel: RegisterViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val user by registerViewModel.userRegister.collectAsState()
        var snackbarVisibleState by remember { mutableStateOf(false) }


        UserInputField(
            value = user.userName,
            label = "Name",
            onValueChange = registerViewModel::updateUserName
        )
        Spacer(modifier = Modifier.height(10.dp))

        UserInputField(
            value = user.userEmail,
            label = "Email Address",
            onValueChange = registerViewModel::updateUserEmail
        )
        Spacer(modifier = Modifier.height(10.dp))

        UserInputField(
            value = user.userPassword,
            label = "Password",
            onValueChange = registerViewModel::updateUserPassword
        )
        Spacer(modifier = Modifier.height(10.dp))

        PasswordRow()
        Spacer(modifier = Modifier.height(60.dp))

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
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
    textFieldValue.value = TextFieldValue(value)

    OutlinedTextField(
        value = textFieldValue.value,
        onValueChange = { newValue ->
            textFieldValue.value = newValue
            onValueChange(newValue.text)
        },
        label = { Text(text = label, color = Color(0xFF928A9C)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = editTextBackgroundColor,
            textColor = Color(0xFFC4C4C4),
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
    LoginAndRegisterScreen()
}