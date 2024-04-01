package com.example.storeroom.ui.addlink.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.storeroom.util.StoreroomColor

const val ADD_CATEGORY = "Add Category"

@Composable
fun DropdownTextField(
    onItemSelected: (String?) -> Unit,
    onAddCategoryClicked: () -> Unit,
    items: List<String?>,
    categoryName: String?,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val updatedItems = listOf(ADD_CATEGORY) + items.filterNotNull()
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Column(
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(StoreroomColor.storeRoomDarkWhite),
            border = BorderStroke(0.5.dp, Color(0xFFCCC9C9)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .onGloballyPositioned { layoutCoordinates ->
                        rowSize = layoutCoordinates.size.toSize()
                    }
                    .fillMaxSize()
                    .clickable(onClick = { expanded = true })
                    .background(StoreroomColor.storeRoomDarkWhite),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text =
                    if(categoryName.orEmpty().isNotEmpty()) categoryName.orEmpty()
                    else if (selectedIndex in updatedItems.indices) updatedItems[selectedIndex]
                    else "Select a category",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    color = Color(0xFF928A9C)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { rowSize.width.toDp() })
                    .heightIn(max = 300.dp)
                    .background(StoreroomColor.storeRoomDarkWhite)
            ) {
                updatedItems.forEachIndexed { index, string ->
                    DropdownMenuItem(
                        onClick = {
                            if (string == ADD_CATEGORY) {
                                onAddCategoryClicked()
                            } else {
                                selectedIndex = index
                                expanded = false
                                onItemSelected(string)
                            }
                        }) {
                        if (string == ADD_CATEGORY) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Add, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text(text = string)
                            }
                        } else {
                            Text(text = string)
                        }
                    }
                }
            }
        }
    }
}
