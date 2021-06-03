package id.bangkit2021.submissionekspert.core.data.remote

import android.util.Log
import id.bangkit2021.submissionekspert.core.data.remote.api.ApiResponse
import id.bangkit2021.submissionekspert.core.data.remote.api.ApiService
import id.bangkit2021.submissionekspert.core.data.remote.response.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovieList(): Flow<ApiResponse<List<MovieModel>>> {

        return flow {
            try {
                val response = apiService.getMovieData()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}