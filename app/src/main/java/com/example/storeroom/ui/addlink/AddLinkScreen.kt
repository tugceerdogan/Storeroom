package com.example.storeroom.ui.addlink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.ui.addlink.components.DropdownTextField
import com.example.storeroom.ui.loginregister.components.UserButton
import com.example.storeroom.ui.loginregister.components.UserInputTextField
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomColor
import kotlinx.coroutines.delay

@Composable
fun AddLinkScreen(
    navHostController: NavHostController,
    addLinkScreenViewModel: AddLinkScreenViewModel = hiltViewModel(),
    categoryName: String?
) {

    val userLinkInfo by addLinkScreenViewModel.userLinkInfo.collectAsStateWithLifecycle()

    val addLinkResult by addLinkScreenViewModel.addLinkResult.collectAsStateWithLifecycle()

    val textLinkValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.url)) }
    val linkError = remember { mutableStateOf("") }

    val textCategoryValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.category)) }
    val textNoteValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.note)) }

    var showSnackbar = remember { mutableStateOf(false) }

    val categories = addLinkScreenViewModel.categories.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Add Link",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 20.sp,
                    color =  Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(StoreroomColor.storeRoomAddLinkButtonColor)
                    .padding(start = 32.dp, top = 16.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            UserInputTextField(
                value = textLinkValue.value,
                label = "Enter Link",
                onValueChange = {
                    textLinkValue.value = it
                    addLinkScreenViewModel.updateUrl(it.text)
                },
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            if (linkError.value.isNotEmpty()) {
                Text(
                    text = linkError.value,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 32.dp, top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            DropdownTextField(
                items = categories.value,
                onItemSelected = { selectedItem ->
                    addLinkScreenViewModel.updateCategory(selectedItem.orEmpty())
                    textCategoryValue.value = TextFieldValue(selectedItem.orEmpty())
                },
                onAddCategoryClicked = {
                    navHostController.navigate(Screen.CreateCategory.route)
                },
                categoryName =
                textCategoryValue.value.text.ifEmpty { categoryName },
            )
            Spacer(modifier = Modifier.height(25.dp))
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
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            UserButton(
                onClick = {
                    if (textLinkValue.value.text.isBlank() || textCategoryValue.value.text.isBlank()) {
                        showSnackbar.value = true
                    } else if (!addLinkScreenViewModel.isValidLink(textLinkValue.value.text)) {
                        linkError.value = "Does not match the link format!"
                    } else {
                        linkError.value = ""
                        addLinkScreenViewModel.addLinkToUser()
                        navHostController.navigate(Screen.Home.route)
                    }
                },
                text = "Save",
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .height(50.dp),
                color = Color(0xFF6C97B0)
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

@Preview(showBackground = true)
@Composable
fun PreviewAddLinkScreen() {
    val navHostController = rememberNavController()
    AddLinkScreen(navHostController, categoryName = "")
}