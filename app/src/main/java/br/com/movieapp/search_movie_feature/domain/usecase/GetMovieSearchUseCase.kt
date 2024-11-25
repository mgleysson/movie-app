package br.com.movieapp.search_movie_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieSearchUseCase {
    fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String)
}

class GetMovieSearchUseCaseImpl @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository
) : GetMovieSearchUseCase {
    override fun invoke(params: GetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> {
        return movieSearchRepository.getSearchMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }

}