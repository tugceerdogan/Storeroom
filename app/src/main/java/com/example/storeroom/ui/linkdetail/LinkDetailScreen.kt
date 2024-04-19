package com.example.storeroom.ui.linkdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.storeroom.util.BottomNavigationWrapper
@Composable
fun LinkDetailScreen(
    navHostController: NavHostController,
) {
    BottomNavigationWrapper(navHostController) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            PreviewLinkCard(url = "https://www.youtube.com/watch?v=Czzum6B1Bfo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLinkDetailScreen() {
    val navHostController = rememberNavController()
    LinkDetailScreen(navHostController)
}
