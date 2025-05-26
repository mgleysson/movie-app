package br.com.movieapp.movie_detail_feature.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.Constants
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    // Isolates the logic of accessing the movie ID within the view model, making the screen simpler
    private val movieId = savedStateHandle.get<Int>(key = Constants.MOVIE_DETAIL_ARGUMENT_KEY)

    init {
        movieId?.let {
            checkedFavorite(MovieDetailEvent.CheckedFavorite(it))
            getMovieDetail(MovieDetailEvent.GetMovieDetail(it))
        }
    }

    private fun getMovieDetail(event: MovieDetailEvent.GetMovieDetail) {
        handleEvent(event)
    }

    private fun checkedFavorite(event: MovieDetailEvent.CheckedFavorite) {
        handleEvent(event)
    }

    fun toggleFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White)
            handleEvent(MovieDetailEvent.AddFavorite(movie))
        else
            handleEvent(MovieDetailEvent.RemoveFavorite(movie))
    }

    private fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data?.second,
                                    results = resultData.data?.first ?: emptyFlow()
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.e?.message.toString()
                                )
                                UtilFunctions.logError(
                                    "DETAIL-ERROR",
                                    resultData.e?.message.toString()
                                )
                            }

                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
            }
            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(params = AddMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Error on add favorite")
                            }
                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(IsMovieFavoriteUseCase.Params(
                        event.movieId
                    )).collectLatest { result: ResultData<Boolean> ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = if (result.data == true)
                                    uiState.copy(iconColor = Color.Red)
                                else
                                    uiState.copy(iconColor = Color.White)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Error on checked favorite")
                            }
                            ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(DeleteMovieFavoriteUseCase.Params(
                        event.movie
                    )).collectLatest { result: ResultData<Unit> ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Error on remove favorite")
                            }
                            ResultData.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}