package com.example.storeroom.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.ui.home.HomeScreen
import com.example.storeroom.ui.loginregister.LoginAndRegisterScreen
import com.example.storeroom.ui.categorydetail.CategoryDetailScreen
import com.example.storeroom.ui.addlink.AddLinkScreen
import com.example.storeroom.ui.categoryList.CategoryStaggeredListScreen
import com.example.storeroom.ui.createcategory.CreateCategoryScreen
import com.example.storeroom.ui.favorite.FavoriteScreen
import com.example.storeroom.ui.linkdetail.LinkDetailScreen
import com.example.storeroom.ui.profile.ProfileScreen
import com.example.storeroom.ui.search.SearchScreen
import com.example.storeroom.ui.splash.SplashScreen
import com.example.storeroom.util.Screen

@Composable
fun ApplicationNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navHostController = navController)
        }
        composable(Screen.Login.route) {
            LoginAndRegisterScreen(navHostController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navController)
        }
        composable(Screen.CategoryList.route) {
            CategoryStaggeredListScreen(navHostController = navController)
        }
        composable(Screen.CategoryDetail.ROUTE_TEMPLATE) { backStackEntry ->
            val categoryName =
                backStackEntry.arguments?.getString(Screen.CategoryDetail.ARG_CATEGORY_NAME)
            CategoryDetailScreen(navHostController = navController, categoryName = categoryName)
        }
        composable(Screen.Search.route) {
            SearchScreen(navHostController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navHostController = navController)
        }
        composable(Screen.Link.ROUTE_TEMPLATE) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString(Screen.Link.ARG_CATEGORY_NAME)
            AddLinkScreen(navHostController = navController, categoryName = categoryName)
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(navHostController = navController)
        }
        composable(Screen.LinkDetail.route) {
            LinkDetailScreen(navHostController = navController)
        }
        composable(Screen.CreateCategory.route) {
            CreateCategoryScreen(navHostController = navController)
        }
    }
}
