package id.bangkit2021.submissionekspert.core.data

import id.bangkit2021.submissionekspert.core.data.remote.api.ApiResponse
import id.bangkit2021.submissionekspert.core.data.remote.response.MovieModel
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.domain.repository.IMovieRepository
import id.bangkit2021.submissionekspert.core.utils.AppExecutors
import id.bangkit2021.submissionekspert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: id.bangkit2021.submissionekspert.core.data.remote.RemoteDataSource,
    private val localDataSource: id.bangkit2021.submissionekspert.core.data.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getMovieList(): Flow<id.bangkit2021.submissionekspert.core.data.Resource<List<Movie>>> =
        object :
            id.bangkit2021.submissionekspert.core.data.NetworkBoundResource<List<Movie>, List<MovieModel>>() {
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