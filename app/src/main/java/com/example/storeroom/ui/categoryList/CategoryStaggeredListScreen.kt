package com.example.storeroom.ui.categoryList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.search.SearchBar
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.StoreroomFont

@Composable
fun CategoryStaggeredListScreen(
    navHostController: NavHostController,
    viewModel: CategoryStaggeredListViewModel = hiltViewModel()
) {

    val categories = viewModel.categories.collectAsStateWithLifecycle()
    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StoreroomColor.storeRoomGray)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        SearchBar(
            value = searchText.value,
            onValueChange = { newText ->
                searchText.value = newText
            },
            navHostController = navHostController,
            backgroundColor = StoreroomColor.storeRoomGray
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Categories",
            style = MaterialTheme.typography.body2.copy(
                color = Color.Black,
                fontSize = 25.sp,
                fontFamily = StoreroomFont.customBoldFont
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
        AllCategoriesList(categories = categories.value, navHostController = navHostController)
    }
}

@Composable
fun AllCategoriesList(categories: List<String?>, navHostController: NavHostController) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items(categories.distinct().size) { index ->
            CategoryItem(item = categories.distinct()[index], navHostController = navHostController)
        }
    }
}

@Composable
fun CategoryItem(item: String?, navHostController: NavHostController) {
    val cardHeight = remember { listOf(80.dp, 100.dp, 120.dp, 140.dp, 160.dp).random() }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(cardHeight)
            .fillMaxWidth()
            .clickable {
                val route = Screen.CategoryDetail(item).route
                navHostController.navigate(route)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1.copy(
                    color = StoreroomColor.storeRoomDarkBlue,
                    fontSize = 25.sp,
                    fontFamily = StoreroomFont.customBoldFont
                ),
            )
        }
    }
}

/*@Preview
@Composable
fun AllCategoriesList() {
    AllCategoriesList(List(50) { "Category Name" })
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem("Category Name")
}*/
