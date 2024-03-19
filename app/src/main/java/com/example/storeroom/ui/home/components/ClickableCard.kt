package com.example.storeroom.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeroom.util.CategoryItemType
import com.example.storeroom.util.StoreroomFont
import com.example.storeroom.util.StoreroomTheme

@Composable
fun ClickableCard(item: String?, onClick: () -> Unit) {
    val itemType = CategoryItemType.fromString(item ?: "")
    val color = if (itemType == CategoryItemType.SEE_MORE) Color.Gray else Color.White
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(140.dp, 120.dp)
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .background(color)
                .padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item ?: "",
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontFamily = StoreroomFont.customBoldFont
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
                if (itemType == CategoryItemType.SEE_MORE) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Redirect Button")
                }
            }
        }
    }
}
