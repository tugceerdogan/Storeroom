package com.example.storeroom.ui.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.storeroom.util.StoreroomTheme

@Composable
fun EmptyHomeScreen() {
    Text(
        text = "There are no categories yet...",
        style = MaterialTheme.typography.body1.copy(
            fontSize = 20.sp,
            fontFamily = StoreroomTheme.customBoldFont,
            color = StoreroomTheme.termAndPolicyClickableTextColor,
        ),
    )
}
