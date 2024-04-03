package com.example.storeroom.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonAddLinks(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FloatingActionButton(
            backgroundColor = StoreroomColor.storeRoomAddLinkButtonColor,
            onClick = {
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 32.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add a link", tint = Color.White)
        }
    }
}
