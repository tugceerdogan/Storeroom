package com.example.storeroom.ui.loginregister.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomDialog(
    title : String,
    description: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text =title) },
            text = { Text(text = description) },
            confirmButton = {}
        )
    }
}
