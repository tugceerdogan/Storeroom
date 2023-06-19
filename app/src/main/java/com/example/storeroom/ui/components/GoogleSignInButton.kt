package com.example.storeroom.ui.components

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.storeroom.BuildConfig
import com.example.storeroom.R
import com.example.storeroom.ui.login.LoginViewModel
import com.example.storeroom.util.StoreroomTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun GoogleSignInButton(
    viewModel: LoginViewModel,
    onSignedIn: () -> Unit,
    onError: () -> Unit
) {
    val context = LocalContext.current
    val user by viewModel.user.observeAsState()
    val googleIcon = painterResource(R.drawable.google_icon)

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
            onError.invoke()
        }
    }

    Button(
        onClick = {
            val signInIntent = client.signInIntent
            signInLauncher.launch(signInIntent)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = StoreroomTheme.googleButtonColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Image(
            painter = googleIcon,
            contentDescription = "Google",
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
        )
    }
}
