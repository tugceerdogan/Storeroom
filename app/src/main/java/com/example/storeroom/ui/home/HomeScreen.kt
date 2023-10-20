package com.example.storeroom.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.home.components.BackgroundComponent
import com.example.storeroom.ui.home.components.ButtonAddLinks
import com.example.storeroom.ui.home.components.CategoriesFlowRowList
import com.example.storeroom.ui.home.components.EmptyHomeScreen
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.StoreroomTheme

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val categories = viewModel.categories.collectAsStateWithLifecycle()

    BottomNavigationWrapper(navHostController) {
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
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "STOREROOM",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 40.sp,
                        fontFamily = StoreroomTheme.customBoldFont,
                        color = StoreroomTheme.termAndPolicyClickableTextColor,
                    ),
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Your Digital Archive..",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 10.sp,
                        color = StoreroomTheme.termAndPolicyClickableTextColor,
                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))
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

