package com.example.storeroom.ui.createcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.R
import com.example.storeroom.ui.loginregister.components.UserButton
import com.example.storeroom.ui.loginregister.components.UserInputTextField
import com.example.storeroom.util.Screen
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.StoreroomFont

@Composable
fun CreateCategoryScreen(
    navHostController: NavHostController
) {
    val categoryName = remember { mutableStateOf(TextFieldValue("")) }

    val suggestList = listOf(
        "My Reading List",
        "Important documents",
        "Recipe Links",
        "Medium Articles"
    )

    Column(Modifier.background(color = Color.LightGray)) {
        ComposedAppBar(onBackClicked = {
            navHostController.navigateUp()
        })
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                UserInputTextField(
                    value = categoryName.value,
                    label = "Enter Category Name",
                    onValueChange = { newValue ->
                        categoryName.value = newValue
                    },
                    modifier = Modifier.padding(top = 10.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = "Icon description",
                        tint = StoreroomColor.storeRoomBrown
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "You can also use the category you created for the links you add later.",
                        modifier = Modifier.padding(vertical = 15.dp, horizontal = 1.dp),
                        color = StoreroomColor.storeRoomBrown
                    )
                }
                Text(
                    text = "Suggested Names",
                    modifier = Modifier.padding(bottom = 15.dp),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 24.sp,
                        fontFamily = StoreroomFont.customBoldFont
                    )
                )
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(start = 4.dp)) {
                    items(suggestList.size) { index ->

                        val currentItem = suggestList[index]

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    categoryName.value = TextFieldValue(currentItem)
                                }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_book),
                                contentDescription = "Icon",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = currentItem,
                                color = StoreroomColor.storeRoomBrown
                            )
                        }
                    }
                }
                UserButton(
                    onClick = {
                        if (categoryName.value.text.isNotEmpty()) {
                            val route = Screen.Link(categoryName.value.text).route
                            navHostController.navigate(route)
                        }
                    },
                    text = "Save",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    color = Color.Green
                )
            }
        }
    }
}

@Composable
fun ComposedAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text ="Create Category", fontSize = 16.sp) },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 4.dp
    )
}

@Preview(showBackground = true)
@Composable
fun CreateCategoryScreenPreview() {
    val navHostController = rememberNavController()
    CreateCategoryScreen(navHostController)
}