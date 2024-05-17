package com.example.storeroom.ui.linkdetail

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.storeroom.ui.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LinkDetailScreen(
    viewModel: LinkDetailViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel((LocalContext.current as ComponentActivity)),
) {
    val linkItem = sharedViewModel.selectedLinkItem.collectAsStateWithLifecycle()

    LaunchedEffect(linkItem.value?.linkId) {
        viewModel.fetchUserLinkInfo(linkItem.value?.linkId.orEmpty())
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            PreviewLinkCard(url = "https://www.youtube.com/watch?v=Czzum6B1Bfo")
        }
        FavoriteButton(
            linkId = linkItem.value?.linkId.orEmpty(),
            viewModel = viewModel,
            modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLinkDetailScreen() {
    LinkDetailScreen()
}
