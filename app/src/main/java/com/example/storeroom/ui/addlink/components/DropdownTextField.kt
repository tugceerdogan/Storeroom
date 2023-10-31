package com.example.storeroom.ui.addlink.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomTheme

@Composable
fun DropdownTextField(
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Enter Category", "B", "C", "D", "E", "F")
    var selectedIndex by remember { mutableStateOf(0) }
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(56.dp)
                .background(StoreroomTheme.editTextBackgroundColor),
            border = BorderStroke(0.5.dp, Color(0xFFCCC9C9)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { expanded = true })
                    .background(StoreroomTheme.editTextBackgroundColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = items[selectedIndex],
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
                    .fillMaxWidth()
                    .background(StoreroomTheme.editTextBackgroundColor)
            ) {
                items.forEachIndexed { index, string ->
                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            expanded = false
                            onItemSelected(string)
                        }) {
                        Text(text = string)
                    }
                }
            }
        }
    }
}
