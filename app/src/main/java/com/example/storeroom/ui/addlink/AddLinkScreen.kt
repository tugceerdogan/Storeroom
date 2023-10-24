package com.example.storeroom.ui.addlink

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.loginregister.components.UserButton
import com.example.storeroom.ui.loginregister.components.UserInputTextField
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.Screen

@Composable
fun AddLinkScreen(
    navHostController: NavHostController,
    addLinkScreenViewModel: AddLinkScreenViewModel = hiltViewModel()
) {

    val userLinkInfo by addLinkScreenViewModel.userLinkInfo.collectAsStateWithLifecycle()

    val addLinkResult by addLinkScreenViewModel.addLinkResult.collectAsStateWithLifecycle()

    val textLinkValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.url)) }
    val textCategoryValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.category)) }

    BottomNavigationWrapper(navHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                UserInputTextField(
                    value = textLinkValue.value,
                    label = "Enter Link",
                    onValueChange = {
                        textLinkValue.value = it
                        addLinkScreenViewModel.updateUrl(it.text)
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))

                UserInputTextField(
                    value = textCategoryValue.value,
                    label = "Enter Category",
                    onValueChange = {
                        textCategoryValue.value = it
                        addLinkScreenViewModel.updateCategory(it.text)
                    },
                )

                Spacer(modifier = Modifier.height(30.dp))

                UserButton(
                    onClick = {
                        addLinkScreenViewModel.addLinkToUser()
                        navHostController.navigate(Screen.Home.route)
                    },
                    text = "Save"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLinkScreen() {
    /*    AddLinkScreen()*/
}