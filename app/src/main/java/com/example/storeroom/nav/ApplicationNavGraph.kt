package com.example.storeroom.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.ui.home.HomeScreen
import com.example.storeroom.ui.loginregister.LoginAndRegisterScreen
import com.example.storeroom.ui.categorydetail.CategoryDetailScreen
import com.example.storeroom.ui.link.LinkScreen
import com.example.storeroom.ui.profile.ProfileScreen
import com.example.storeroom.ui.search.SearchScreen
import com.example.storeroom.util.Screen

@Composable
fun ApplicationNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginAndRegisterScreen(navHostController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navController)
        }

        composable(Screen.CategoryDetail.route) {
            CategoryDetailScreen(navHostController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navHostController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navHostController = navController)
        }
        composable(Screen.Link.route) {
            LinkScreen()
        }
    }
}
