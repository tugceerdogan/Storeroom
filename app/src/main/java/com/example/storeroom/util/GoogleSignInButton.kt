package com.example.storeroom.util

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.storeroom.BuildConfig
import com.example.storeroom.ui.login.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun GoogleSignInButton(
    viewModel: LoginViewModel,
    onSignedIn: () -> Unit
) {
    val context = LocalContext.current
    val user by viewModel.user.observeAsState()

    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.CLIENT_ID)
        .requestEmail()
        .build()

    val client = GoogleSignIn.getClient(context, googleSignInOptions)

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.signInWithGoogle(result.data)
            onSignedIn.invoke()
        } else {
            println("ERROR")
        }
    }

    Button(onClick = {
        val signInIntent = client.signInIntent
        signInLauncher.launch(signInIntent)
    }) {
        Text("Sign in with Google")
    }
}
