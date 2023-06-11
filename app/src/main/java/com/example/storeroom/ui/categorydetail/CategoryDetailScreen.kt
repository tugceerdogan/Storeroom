package com.example.storeroom.ui.categorydetail

import androidx.compose.foundation.layout.*
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
import com.example.storeroom.util.StoreroomTheme

@Composable
fun CategoryDetailScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(300.dp))
        Text(
            text = "CATEGORY DETAIL SCREEN",
            style = MaterialTheme.typography.body1.copy(
                color = Color.Black,
                fontSize = 25.sp,
                fontFamily = StoreroomTheme.customBoldFont
            ),
            textAlign = TextAlign.Center,
        )
    }
}