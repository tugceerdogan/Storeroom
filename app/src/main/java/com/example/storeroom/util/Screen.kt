package com.example.storeroom.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Favorite : Screen("favorite", "Favorite", Icons.Filled.Favorite)
    data class CategoryDetail(val categoryName: String?) : Screen("categoryDetail/$categoryName") {
        companion object {
            const val ROUTE_TEMPLATE = "categoryDetail/{categoryName}"
            const val ARG_CATEGORY_NAME = "categoryName"
        }
    }
    object LinkDetail : Screen("linkDetail")
    object CategoryList : Screen("categoryList")
    object Link : Screen("link")
    object Search : Screen("search", "Search", Icons.Filled.Search)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    object CreateCategory: Screen("createcategory", "CreateCategory")
}
