package com.example.storeroom.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    object Login : Screen("login")
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object CategoryDetail : Screen("categoryDetail")
    object Link : Screen("link")
    object Search : Screen("search", "Search", Icons.Filled.Search)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}
