package com.example.storeroom.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.storeroom.ui.home.components.ButtonAddLinks
import com.example.storeroom.ui.home.components.CategoriesFlowRowList

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        CategoriesFlowRowList(navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        ButtonAddLinks()
    }
}
