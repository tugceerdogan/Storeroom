package com.example.storeroom.ui.loginregister


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.storeroom.ui.loginregister.components.CustomDialog
import com.example.storeroom.ui.loginregister.components.LoginTabScreen
import com.example.storeroom.ui.loginregister.components.RegisterTabScreen
import com.example.storeroom.util.StoreroomColor.storeRoomBrown
import com.example.storeroom.util.StoreroomColor.storeRoomDarkBlue
import com.example.storeroom.util.StoreroomColor.storeRoomDarkGray
import com.example.storeroom.util.StoreroomFont.customBoldFont

@Composable
fun LoginAndRegisterScreen(
    navHostController: NavHostController
) {

    val showTermAndPolicyDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText()
        TermAndPolicyText(
            onClick = { showTermAndPolicyDialog.value = true },
            text = "By signing in you are agreeing",
            clickableText = " Storeroom's Terms of Use and Privacy Policy"
        )
        CustomDialog(
            title = "Terms of Use and Privacy Policy",
            description = "Bla bla blaaaaaa",
            showDialog = showTermAndPolicyDialog.value,
            onDismiss = { showTermAndPolicyDialog.value = false }
        )

        Spacer(modifier = Modifier.height(45.dp))
        LoginOrRegisterTabLayout(navHostController)
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
                color = storeRoomBrown,
            )
        ) {
            append(text)
        }
        withStyle(
            style = SpanStyle(
                color = storeRoomDarkBlue,
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
fun LoginOrRegisterTabLayout(navHostController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(horizontal = 30.dp),
            backgroundColor = Color.White,
            contentColor = storeRoomDarkGray,
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
                Text(text = "Login", Modifier.padding(vertical = 10.dp))
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            ) {
                Text(text = "Register", Modifier.padding(vertical = 10.dp))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (selectedTabIndex == 0) {
                LoginTabScreen(navHostController = navHostController)
            } else {
                RegisterTabScreen(navHostController = navHostController)
            }
        }
    }
}