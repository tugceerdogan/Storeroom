package com.example.storeroom.ui.loginregister.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.storeroom.util.StoreroomTheme

@Composable
fun UserPasswordInputField(
    value: TextFieldValue,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    val passwordVisibility = remember { mutableStateOf(false) }

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
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(
                    imageVector = if (passwordVisibility.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = "Toggle Password Visibility"
                )
            }
        }
    )
}
