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
import com.example.storeroom.util.CategoryItemType

private const val MAX_DISPLAY_ITEMS_COUNT = 6
private const val CHUNKED_ITEMS_COUNT = 2

@Composable
fun CategoriesFlowRowList(navHostController: NavHostController, list: List<String?>) {

    val displayItems = if (list.size > MAX_DISPLAY_ITEMS_COUNT) {
        list.take(MAX_DISPLAY_ITEMS_COUNT - 1) + CategoryItemType.SEE_MORE.value
    } else {
        list
    }

    val chunkedItems = displayItems.chunked(CHUNKED_ITEMS_COUNT)

    LazyColumn {
        items(chunkedItems) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (item in rowItems) {
                    val itemType = CategoryItemType.fromString(item ?: "")
                    if (itemType == CategoryItemType.SEE_MORE) {
                        SeeMoreItemCard(item = item, navHostController = navHostController)
                    } else {
                        CategoryItemCard(item = item, navHostController = navHostController)
                    }
                }
            }
        }
    }
}
