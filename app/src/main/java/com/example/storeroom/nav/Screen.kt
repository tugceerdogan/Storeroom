package com.example.storeroom.nav

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object CategoryDetail : Screen("categoryDetail")
}
