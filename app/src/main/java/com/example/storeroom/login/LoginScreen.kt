package com.example.storeroom.login


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeroom.R


val whiteTextStyle = TextStyle(color = Color.White)
val customBoldFont = FontFamily(Font(R.font.majallabold))
val customFont = FontFamily(Font(R.font.majallabold))
val termAndPolicyTextColor = Color(0xFF6B5E5E)
val termAndPolicyClickableTextColor = Color(0xFF0386D0)

@Composable
fun LoginScreen() {
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
        Spacer(modifier = Modifier.height(110.dp))
        UserEmailTextField()
        Spacer(modifier = Modifier.height(10.dp))
        UserPasswordTextField()
        Spacer(modifier = Modifier.height(100.dp))
        UserLoginButton(onClick = {
            snackbarVisibleState.value = true
        })
        LoginSuccesfulSnackbar(
            snackbarVisibleState,
            "Login Successful"
        )
    }
}

@Composable
fun TitleText() {
    Text(
        text = "Login",
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
                fontFamily = customFont,
                fontSize = 20.sp
            )
        ) {
            append(text)
        }
        withStyle(
            style = SpanStyle(
                color = termAndPolicyClickableTextColor,
                textDecoration = TextDecoration.None,
                fontFamily = customFont,
                fontSize = 20.sp
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
fun UserEmailTextField() {
    var userEmail by remember { mutableStateOf("") }

    OutlinedTextField(
        value = userEmail,
        onValueChange = { userEmail = it },
        label = { Text(text = "Email") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    )
}

@Composable
fun UserPasswordTextField() {
    var userPassword by remember { mutableStateOf("") }

    OutlinedTextField(
        value = userPassword,
        onValueChange = { userPassword = it },
        label = { Text(text = "Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    )
}


@Composable
fun UserLoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(48.dp)
    ) {
        Text(text = "Login", style = whiteTextStyle)
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