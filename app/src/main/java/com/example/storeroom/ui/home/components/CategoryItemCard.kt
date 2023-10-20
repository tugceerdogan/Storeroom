package com.example.storeroom.ui.home.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen

@Composable
fun CategoryItemCard(item: String?, navHostController: NavHostController) {
    ClickableCard(item) {
        navHostController.navigate(Screen.CategoryDetail.route)
    }
}
