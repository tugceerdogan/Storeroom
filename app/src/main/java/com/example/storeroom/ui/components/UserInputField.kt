package com.example.storeroom.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomTheme

@Composable
fun UserInputField(
    value: TextFieldValue,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color(0xFF928A9C)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = StoreroomTheme.editTextBackgroundColor,
            textColor = Color.Black,
            focusedBorderColor = Color(0xFF928A9C),
            unfocusedBorderColor = Color(0xFFCCC9C9)
        ),
        shape = RoundedCornerShape(16.dp),
    )
}
