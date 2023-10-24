package com.example.storeroom.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.storeroom.util.BottomNavigationWrapper
import com.example.storeroom.util.StoreroomTheme

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    BottomNavigationWrapper(navHostController) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(300.dp))
            Text(
                text = "FAVORITE SCREEN",
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = StoreroomTheme.customBoldFont
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}
