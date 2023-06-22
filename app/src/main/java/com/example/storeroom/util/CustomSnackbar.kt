package com.example.storeroom.util

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import kotlinx.coroutines.launch

@Composable
fun CustomSnackbar(
    snackbarText: String,
    errorException: Exception? = null
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
            errorException?.message ?: snackbarText
        )
    }
}