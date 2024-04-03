package com.example.storeroom.ui.loginregister.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomColor
import com.example.storeroom.util.StoreroomTheme

@Composable
fun UserButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier,
    color: Color = StoreroomColor.storeRoomDarkBlue,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        ),
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text, style = StoreroomTheme.whiteTextStyle )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserButton() {
    UserButton(onClick = {}, text = "Save", Modifier.height(40.dp))
}
