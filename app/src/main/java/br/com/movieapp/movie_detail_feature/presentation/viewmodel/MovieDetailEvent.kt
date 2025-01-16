package br.com.movieapp.movie_detail_feature.presentation.viewmodel

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
}