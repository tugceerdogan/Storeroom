package com.example.storeroom.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomTheme

@Composable
fun CategoryItemCard(item: String, navHostController: NavHostController) {
    ClickableCard(item) {
        navHostController.navigate(Screen.CategoryDetail.route)
    }
}

@Composable
fun ClickableCard(item: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(150.dp, 120.dp)
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = StoreroomTheme.customBoldFont
                ),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}
