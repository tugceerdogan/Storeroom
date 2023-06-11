package com.example.storeroom.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navHostController: NavHostController){
    CategoriesFlowRowList()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesFlowRowList() {
    val items = listOf("Item 1", "Item 2 Item 1 Item 1", "A much much longer item", "Item 4 Item 1", "Item 5", "Item 5")
    FlowRow {
        for (item in items) {
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: String) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Text(
            text = item,
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.primary)
                .padding(16.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}
