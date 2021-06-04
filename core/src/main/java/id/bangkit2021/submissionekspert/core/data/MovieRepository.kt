package id.bangkit2021.submissionekspert.core.data

import id.bangkit2021.submissionekspert.core.data.local.LocalDataSource
import id.bangkit2021.submissionekspert.core.data.remote.RemoteDataSource
import id.bangkit2021.submissionekspert.core.data.remote.api.ApiResponse
import id.bangkit2021.submissionekspert.core.data.remote.response.MovieModel
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.domain.repository.IMovieRepository
import id.bangkit2021.submissionekspert.core.utils.AppExecutors
import id.bangkit2021.submissionekspert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getMovieList(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieModel>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localDataSource.getMovieList().map { DataMapper.mapEntitiesToDomain(it) }
            }


            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieModel>>> =
                remoteDataSource.getMovieList()


            override suspend fun saveCallResult(data: List<MovieModel>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()


    override fun setFavoriteMovie(movie: Movie, favorite: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, favorite) }
    }


    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

}