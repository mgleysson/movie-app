package br.com.movieapp.search_movie_feature.presentation.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.search_movie_feature.presentation.components.SearchContent
import br.com.movieapp.search_movie_feature.presentation.models.MovieSearchEvent
import br.com.movieapp.search_movie_feature.presentation.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onSearchEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.search_movies
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onSearchEvent(it)
                },
                onDetail = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )

}