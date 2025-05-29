package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.AsyncImageUrl
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieDetailBackdropImage(
    backdropImageUrl: String,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        AsyncImageUrl(
            modifier = Modifier
                .fillMaxWidth(),
            imageUrl = backdropImageUrl,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}