package com.example.storeroom.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.search.SearchBar
import com.example.storeroom.ui.home.components.BackgroundComponent
import com.example.storeroom.ui.home.components.ButtonAddLinks
import com.example.storeroom.ui.home.components.CategoriesFlowRowList
import com.example.storeroom.ui.home.components.EmptyHomeScreen
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.StoreroomFont

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val categories = viewModel.categories.collectAsStateWithLifecycle()
    val searchText = remember { mutableStateOf("") }

    BottomNavigationWrapper(navHostController) {
        SearchBar(
            value = searchText.value,
            onValueChange = { newText ->
                searchText.value = newText
            },
            navHostController = navHostController,
            isHomePage = true
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BackgroundComponent()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "STOREROOM",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 40.sp,
                        fontFamily = StoreroomFont.customBoldFont,
                        color = StoreroomColor.storeRoomDarkBlue,
                    ),
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "Your Digital Archive..",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 10.sp,
                        color = StoreroomColor.storeRoomDarkBlue,
                    ),
                )
                Spacer(modifier = Modifier.height(15.dp))
                if(categories.value.isNotEmpty()) CategoriesFlowRowList(navHostController, categories.value)
                else {
                    Spacer(modifier = Modifier.height(50.dp))
                    EmptyHomeScreen()
                }
                ButtonAddLinks(navHostController)
            }
        }
    }
}

