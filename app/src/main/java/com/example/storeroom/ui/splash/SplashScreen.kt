package com.example.storeroom.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val isLoggedIn by splashViewModel.isLoggedIn.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        splashViewModel.checkUserLoggedInStatus()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...")
        }
    }

    if (isLoggedIn) navHostController.navigate(Screen.Home.route)
    else  navHostController.navigate(Screen.Login.route)

}
