package com.example.storeroom.ui.categorydetail

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.storeroom.data.link.UserLinkInfo
import com.example.storeroom.ui.SharedViewModel
import com.example.storeroom.ui.search.SearchBar
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.StoreroomFont

@Composable
fun CategoryDetailScreen(
    navHostController: NavHostController,
    viewModel: CategoryDetailViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel((LocalContext.current as ComponentActivity)),
    categoryName: String?,
) {
    val linkItem = viewModel.linkItem.collectAsStateWithLifecycle()
    val itemsInCategory = linkItem.value.filter { it?.category == categoryName }

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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = categoryName ?:"",
            style = MaterialTheme.typography.body2.copy(
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = StoreroomFont.customBoldFont
            ),
            textAlign = TextAlign.End,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        AllLinksList(links = itemsInCategory.map { it }, navHostController = navHostController, sharedViewModel = sharedViewModel)
    }
}

@Composable
fun AllLinksList(links: List<UserLinkInfo?>, navHostController: NavHostController, sharedViewModel: SharedViewModel) {
    LazyColumn {
        items(links) {
            LinkItem(item = it , navHostController = navHostController, sharedViewModel = sharedViewModel)
        }
    }
}

@Composable
fun LinkItem(item: UserLinkInfo?, navHostController: NavHostController, sharedViewModel: SharedViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                sharedViewModel.selectLinkItem(item)
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
                text = item?.url ?: "",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp, bottom = 20.dp, start = 32.dp),
                style = MaterialTheme.typography.body1.copy(
                    color = StoreroomColor.storeRoomDarkBlue,
                    fontSize = 25.sp,
                    fontFamily = StoreroomFont.customBoldFont
                ),
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Redirect Button",
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, end = 32.dp),
                tint = StoreroomColor.storeRoomDarkBlue,
            )
        }
    }
}