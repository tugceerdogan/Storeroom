package com.example.storeroom.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.ui.home.HomeScreen
import com.example.storeroom.ui.LoginAndRegisterScreen
import com.example.storeroom.ui.categorydetail.CategoryDetailScreen
import com.example.storeroom.ui.login.LoginViewModel
import com.example.storeroom.ui.register.RegisterViewModel

@Composable
fun ApplicationNavGraph() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginAndRegisterScreen(
                loginViewModel = loginViewModel,
                registerViewModel = registerViewModel,
                navHostController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navController)
        }

        composable(Screen.CategoryDetail.route) {
            CategoryDetailScreen(navHostController = navController)
        }
    }
}
