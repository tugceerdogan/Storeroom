package com.example.storeroom.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.storeroom.R

object StoreroomTheme {
    val whiteTextStyle = TextStyle(color = Color.White, fontSize = 20.sp)
    val customBoldFont = FontFamily(Font(R.font.majallabold))
    val termAndPolicyTextColor = Color(0xFF6B5E5E)
    val termAndPolicyClickableTextColor = Color(0xFF0386D0)
    val unselectTabColor = Color(0xFFA6A6A6)
    val rememberPasswordTextColor = Color(0xFF6B5E5E)
    val editTextBackgroundColor = Color(0xFFF9F9F9)
    val googleButtonColor = Color(0xFF9BD8FC)
}
