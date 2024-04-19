package com.example.storeroom.ui.linkdetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

@Composable
fun PreviewLinkCard(url: String) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(url) {
        isLoading = true
        withContext(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect(url).get()
                title = doc.select("meta[property=og:title]").attr("content")
                description = doc.select("meta[property=og:description]").attr("content")
                imageUrl = doc.select("meta[property=og:image]").attr("content")
            } catch (e: Exception) {
                title = "Failed to load data"
                description = "Please check your internet connection or the url"
            } finally {
                isLoading = false
            }
        }
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else if (title.isNotEmpty()) {
        Card(
            elevation = 4.dp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
        ) {
            Column {
                if (imageUrl.isNotEmpty()) {
                    val imagePainter =
                        rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    // placeholder(R.drawable.placeholder_image)
                                    // error(R.drawable.error_image)
                                }).build()
                        )
                    Image(
                        painter = imagePainter,
                        contentDescription = title
                    )
                }
                Text(text = title, style = MaterialTheme.typography.h6)
                Text(text = description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPreviewLinkCard() {
    PreviewLinkCard("https://www.youtube.com/watch?v=Czzum6B1Bfo")
}
