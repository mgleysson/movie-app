package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import br.com.movieapp.movie_favorite_feature.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetailMovie: (movieId: Int) -> Unit
) {

    val movies = uiState.movies

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.favorite_movies
            )
        },
        content = { paddingValues ->
            MovieFavoriteContent(
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )

}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        uiState = MovieFavoriteState(),
        navigateToDetailMovie = {}
    )
}