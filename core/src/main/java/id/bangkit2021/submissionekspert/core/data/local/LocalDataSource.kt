package id.bangkit2021.submissionekspert.core.data.local

import id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity
import id.bangkit2021.submissionekspert.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: id.bangkit2021.submissionekspert.core.data.local.room.MovieDao){

    fun getMovieList() : Flow<List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>> = movieDao.getMovieList()

    fun getFavoriteMovie() : Flow<List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieEntity: List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>)  = movieDao.insertMovies(movieEntity)

    fun setFavoriteMovie(movieEntity: id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity, favorite : Boolean){
        movieEntity.favorite = favorite
        movieDao.updateMovies(movieEntity)
    }
}