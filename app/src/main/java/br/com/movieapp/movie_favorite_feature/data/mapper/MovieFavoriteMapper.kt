package br.com.movieapp.movie_favorite_feature.data.mapper

import br.com.movieapp.core.data.local.entity.MovieEntity
import br.com.movieapp.core.domain.model.Movie

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.movieId,
        title = movieEntity.title,
        imageUrl = movieEntity.imageUrl
    )
}

fun Movie.toMovieEntity() = MovieEntity(id, title, imageUrl)