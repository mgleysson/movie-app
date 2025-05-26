package br.com.movieapp.movie_favorite_feature.di

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(localDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideAddMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

}