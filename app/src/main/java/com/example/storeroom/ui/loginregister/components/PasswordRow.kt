package com.example.storeroom.ui.loginregister.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomColor

@Composable
fun PasswordRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Checkbox(checked = false, onCheckedChange = { })
        Text(
            text = "Remember Password",
            color = StoreroomColor.storeRoomBrown
        )
        Text(
            text = "Forget Password",
            modifier = Modifier.padding(start = 58.dp),
            color = StoreroomColor.storeRoomDarkBlue
        )
    }
}
