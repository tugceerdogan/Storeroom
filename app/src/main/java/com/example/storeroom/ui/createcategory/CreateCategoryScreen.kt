package com.example.storeroom.ui.createcategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.util.Screen

@Composable
fun CreateCategoryScreen(
    navHostController: NavHostController,
    viewModel: CreateCategoryViewModel = hiltViewModel(),
) {
    val categoryName = remember { mutableStateOf("") }

    val items = listOf("Tugce", "Tugce", "Tugce", "Tugce")

    Column {
        ComposedAppBar(onBackClicked = { navHostController.navigate(Screen.Link.route) })
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                EnterCategoryNameTextField(
                    value = categoryName.value,
                    onValueChange = { newText ->
                        categoryName.value = newText
                    },
                )
                Text(
                    text = "You can also use the category you created for the links you add later.",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Suggested Names",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                LazyColumn {
                    items(items.size) { item ->
                        Text(text = items[1]) // Adjust according to your item structure
                    }
                }
                Button(
                    onClick = {
                        if (categoryName.value.isNotEmpty()) {
                            navHostController.navigateUp()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun ComposedAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text("Create Category") },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 4.dp
    )
}

@Composable
fun EnterCategoryNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        textStyle = TextStyle(color = Color.Black),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = "Enter category name")
        },
        modifier = Modifier
            .wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun CreateCategoryScreenPreview() {
    val navHostController = rememberNavController()
    CreateCategoryScreen(navHostController)
}