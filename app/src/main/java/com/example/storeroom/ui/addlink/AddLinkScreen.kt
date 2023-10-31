package com.example.storeroom.ui.addlink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.addlink.components.DropdownTextField
import com.example.storeroom.ui.loginregister.components.UserButton
import com.example.storeroom.ui.loginregister.components.UserInputTextField
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.Screen
import kotlinx.coroutines.delay

@Composable
fun AddLinkScreen(
    navHostController: NavHostController,
    addLinkScreenViewModel: AddLinkScreenViewModel = hiltViewModel()
) {

    val userLinkInfo by addLinkScreenViewModel.userLinkInfo.collectAsStateWithLifecycle()

    val addLinkResult by addLinkScreenViewModel.addLinkResult.collectAsStateWithLifecycle()

    val textLinkValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.url)) }
    val textCategoryValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.category)) }
    val textNoteValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.note)) }

    var showSnackbar = remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    BottomNavigationWrapper(navHostController) {
        Spacer(modifier = Modifier.padding(top = (screenHeight / 4).dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                UserInputTextField(
                    value = textLinkValue.value,
                    label = "Enter Link",
                    onValueChange = {
                        textLinkValue.value = it
                        addLinkScreenViewModel.updateUrl(it.text)
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                DropdownTextField { selectedItem ->
                    textCategoryValue.value = TextFieldValue(selectedItem)
                    addLinkScreenViewModel.updateCategory(selectedItem)
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Optional",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                )
                UserInputTextField(
                    value = textNoteValue.value,
                    label = "Enter Note",
                    onValueChange = {
                        textNoteValue.value = it
                        addLinkScreenViewModel.updateNote(it.text)
                    },
                )

                Spacer(modifier = Modifier.height(30.dp))

                UserButton(
                    onClick = {
                        if (textLinkValue.value.text.isBlank() || textCategoryValue.value.text.isBlank()) {
                            showSnackbar.value = true
                        } else {
                            addLinkScreenViewModel.addLinkToUser()
                            navHostController.navigate(Screen.Home.route)
                        }
                    },
                    text = "Save"
                )
            }

            if (showSnackbar.value) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 64.dp)
                        .background(Color(0xFF333333)),
                    action = {
                        TextButton(onClick = { showSnackbar.value = false }) {
                            Text(text = "OK", color = Color(0xFFFFFFFF))
                        }
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Please fill in the blank fields!")
                }
                LaunchedEffect(key1 = showSnackbar.value) {
                    delay(3000L)
                    showSnackbar.value = false
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLinkScreen() {
    /*    AddLinkScreen()*/
}