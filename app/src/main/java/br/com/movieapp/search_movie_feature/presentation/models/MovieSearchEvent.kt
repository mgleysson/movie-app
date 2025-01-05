package br.com.movieapp.search_movie_feature.presentation.models

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}