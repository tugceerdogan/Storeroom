package com.example.storeroom.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CategoriesFlowRowList(navHostController: NavHostController) {
    val items = listOf(
        "Category 1",
        "Category 2",
        "Category 3738478669",
        "Category AGAHSDFH Category AGAHSDFH",
        "Category",
        "Category XXX"
    )
    val chunkedItems = items.chunked(2)

    LazyColumn {
        items(chunkedItems) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (item in rowItems) {
                    CategoryItemCard(item = item, navHostController = navHostController)
                }
            }
        }
    }
}
