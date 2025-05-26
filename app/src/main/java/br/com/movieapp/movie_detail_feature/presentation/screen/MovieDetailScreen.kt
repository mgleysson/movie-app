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
    id: Int?,
    uiState: MovieDetailState,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit,
    toggleFavorite: (movie: Movie) -> Unit,
    checkedFavorite: (MovieDetailEvent.CheckedFavorite) -> Unit
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    /*
        Bloco de código dentro do LaunchedEffect será executado de forma assíncrona, a
        chave key1=true significa que sempre que a MovieDetailScreen for chamada, o
        LaunchedEffect será executado
     */
    LaunchedEffect(key1 = true) {
        if (id != null) {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(id))
            checkedFavorite(MovieDetailEvent.CheckedFavorite(id))
        }
    }

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