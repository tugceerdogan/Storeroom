package com.example.storeroom.ui.categoryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.home.HomeViewModel
import com.example.storeroom.util.StoreroomTheme

@Composable
fun CategoryListScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val categories = viewModel.categories.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "CATEGORIES",
            style = MaterialTheme.typography.body1.copy(
                color = Color.Black,
                fontSize = 25.sp,
                fontFamily = StoreroomTheme.customBoldFont
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(8.dp))
        AllCategoriesList(categories = categories.value)
    }
}

@Composable
fun AllCategoriesList(categories: List<String?>) {
    LazyColumn {
        items(categories) {
            CategoryItem(item = it)
        }
    }
}

@Composable
fun CategoryItem(item: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = item ?: "",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun AllCategoriesList() {
    AllCategoriesList(listOf("Category Name", "Category Name"))
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem("Category Name")
}
