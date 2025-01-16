package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants
import br.com.movieapp.movie_detail_feature.presentation.screen.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.presentation.viewmodel.MovieDetailViewModel
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
                navigateToDetailMovie = { movieId ->
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId))
                }
            )
        }

        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()

            MovieSearchScreen(
                uiState = viewModel.uiState,
                onSearchEvent = viewModel::search,
                onFetch = viewModel::fetch,
                navigateToDetailMovie = { movieId ->
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId))
                }
            )
        }

        composable(BottomNavItem.MovieFavorite.route) {

        }

        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()

            MovieDetailScreen(
                uiState = viewModel.uiState,
                id = it.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENT_KEY),
                getMovieDetail = viewModel::getMovieDetail
            )

        }
    }
}