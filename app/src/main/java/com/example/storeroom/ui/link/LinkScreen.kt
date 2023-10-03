package com.example.storeroom.ui.link

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
import com.example.storeroom.ui.loginregister.components.UserButton
import com.example.storeroom.ui.loginregister.components.UserInputTextField

@Composable
fun LinkScreen(linkAddScreenViewModel: LinkAddScreenViewModel = hiltViewModel()) {

    val userLinkInfo by linkAddScreenViewModel.userLinkInfo.collectAsStateWithLifecycle()

    val textLinkValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.url)) }
    val textCategoryValue = remember { mutableStateOf(TextFieldValue(userLinkInfo.category)) }

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
                    linkAddScreenViewModel.updateUrl(it.text)
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            UserInputTextField(
                value = textCategoryValue.value,
                label = "Enter Category",
                onValueChange = {
                    textCategoryValue.value = it
                    linkAddScreenViewModel.updateCategory(it.text)
                },
            )

            Spacer(modifier = Modifier.height(30.dp))

            UserButton(
                onClick = { linkAddScreenViewModel.addLinkToUser() },
                text = "Save"
            )
        }
    }
}

@Preview
@Composable
fun PreviewLinkScreen() {
    LinkScreen()
}