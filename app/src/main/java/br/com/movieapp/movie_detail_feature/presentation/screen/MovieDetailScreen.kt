package br.com.movieapp.movie_detail_feature.presentation.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_detail_feature.presentation.viewmodel.MovieDetailEvent
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    toggleFavorite: (movie: Movie) -> Unit,
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detail_movie), color = white)
                },
                backgroundColor = black
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