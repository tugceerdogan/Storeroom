package com.example.storeroom.ui.categorydetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.ui.search.SearchBar
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomTheme

@Composable
fun CategoryDetailScreen(
    navHostController: NavHostController,
    viewModel: CategoryDetailViewModel = hiltViewModel(),
    categoryName: String?
) {
    val linkItem = viewModel.linkItem.collectAsStateWithLifecycle()
    val itemsInCategory = linkItem.value.filter { it?.category == categoryName }

    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StoreroomTheme.veryLightGray)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        SearchBar(
            value = searchText.value,
            onValueChange = { newText ->
                searchText.value = newText
            },
            navHostController = navHostController
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = categoryName ?:"",
            style = MaterialTheme.typography.body2.copy(
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = StoreroomTheme.customBoldFont
            ),
            textAlign = TextAlign.End,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Links",
            style = MaterialTheme.typography.body2.copy(
                color = Color.Black,
                fontSize = 25.sp,
                fontFamily = StoreroomTheme.customBoldFont
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp)
        )
        AllLinksList(urls = itemsInCategory.map { it?.url }, navHostController = navHostController)
    }
}

@Composable
fun AllLinksList(urls: List<String?>, navHostController: NavHostController) {
    LazyColumn {
        items(urls) {
            LinkItem(item = it, navHostController = navHostController)
        }
    }
}

@Composable
fun LinkItem(item: String?, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navHostController.navigate(Screen.LinkDetail.route)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = item ?: "",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp, bottom = 20.dp, start = 32.dp),
                style = MaterialTheme.typography.body1.copy(
                    color = StoreroomTheme.termAndPolicyClickableTextColor,
                    fontSize = 25.sp,
                    fontFamily = StoreroomTheme.customBoldFont
                ),
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Redirect Button",
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, end = 32.dp),
                tint = StoreroomTheme.termAndPolicyClickableTextColor,
            )
        }
    }
}