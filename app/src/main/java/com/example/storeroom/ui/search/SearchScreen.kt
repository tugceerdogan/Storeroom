package com.example.storeroom.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.StoreroomFont

@Composable
fun SearchScreen(navHostController: NavHostController) {

    val searchText = remember { mutableStateOf("") }
    BottomNavigationWrapper(navHostController) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                value = searchText.value,
                onValueChange = { newText ->
                    searchText.value = newText
                },
                navHostController = navHostController,
                isSearchPage = true,
                backgroundColor = Color.Red
            )
            Spacer(modifier = Modifier.height(300.dp))
            Text(
                text = "SEARCH SCREEN",
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = StoreroomFont.customBoldFont
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}
