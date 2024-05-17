package com.example.storeroom.ui.linkdetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoriteButton(
    linkId: String,
    viewModel: LinkDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val userLinkInfo = viewModel.userLinkInfo.collectAsStateWithLifecycle().value
    val isFavorite = userLinkInfo.isFavorite

    IconButton(
        onClick = {
            viewModel.toggleFavoriteStatus(
                !isFavorite,
                linkId
            )
        },
        modifier = modifier
            .padding(16.dp)
    ) {
        if (isFavorite == true) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = androidx.compose.ui.graphics.Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "UnFavorite",
                tint = androidx.compose.ui.graphics.Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteButton() {
    FavoriteButton(
        ""
    )
}
