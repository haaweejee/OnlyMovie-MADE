package id.bangkit2021.submissionekspert.core.domain.repository

import id.bangkit2021.submissionekspert.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovieList(): Flow<id.bangkit2021.submissionekspert.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, favorite: Boolean)
}