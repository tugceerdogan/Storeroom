package com.example.storeroom.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.storeroom.nav.Screen
import com.example.storeroom.util.StoreroomTheme

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        CategoriesFlowRowList(navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        AddLinkFAB()
    }

}

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (item in rowItems) {
                    CategoryItemCard(item = item, navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun CategoryItemCard(item: String, navHostController: NavHostController) {
    ClickableCard(item) {
        navHostController.navigate(Screen.CategoryDetail.route)
    }
}

@Composable
fun AddLinkFAB() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add a link")
            }
        }
    }
}  @Composable
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

