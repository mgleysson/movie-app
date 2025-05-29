package br.com.movieapp.movie_detail_feature.presentation.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    toggleFavorite: (movie: Movie) -> Unit,
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.detail_movie
            )
        },
        content = { paddingValues ->
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = {
                    toggleFavorite(it)
                }
            )
        }
    )

}