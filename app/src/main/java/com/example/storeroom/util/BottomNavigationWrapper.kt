package com.example.storeroom.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationWrapper(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()

    val isLoginPage = currentRoute == Screen.Login.route
    val isDetailPage = currentRoute == Screen.CategoryDetail.ROUTE_TEMPLATE

    if (isLoginPage || isDetailPage) {
        content()
    } else {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    content()
                }
                BottomNavigation(navController)
            }
        }
    }
}
