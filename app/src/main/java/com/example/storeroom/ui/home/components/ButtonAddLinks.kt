package com.example.storeroom.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomTheme
import kotlinx.coroutines.launch

@Composable
fun ButtonAddLinks(navHostController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FloatingActionButton(
                backgroundColor = StoreroomTheme.termAndPolicyClickableTextColor,
                onClick = { navHostController.navigate(Screen.Link.route) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add a link")
            }
        }
    }
}
