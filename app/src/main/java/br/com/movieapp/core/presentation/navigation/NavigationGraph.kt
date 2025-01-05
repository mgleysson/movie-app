package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.movieapp.movie_popular_feature.presentation.screen.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.viewmodel.MoviePopularViewModel
import br.com.movieapp.search_movie_feature.presentation.models.MovieSearchEvent
import br.com.movieapp.search_movie_feature.presentation.screen.MovieSearchScreen
import br.com.movieapp.search_movie_feature.presentation.viewmodel.MovieSearchViewModel

@Composable
fun NavigationGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            MoviePopularScreen(
                uiState = viewModel.uiState,
                navigateToDetailMovie = {

                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()

            MovieSearchScreen(
                uiState = viewModel.uiState,
                onSearchEvent = viewModel::search,
                onFetch = viewModel::fetch,
                navigateToDetailMovie = {}
            )
        }
        composable(BottomNavItem.MovieFavorite.route) {

        }
    }
}