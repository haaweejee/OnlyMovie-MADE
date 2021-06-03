package id.bangkit2021.submissionekspert.core.data.remote.api


import id.bangkit2021.submissionekspert.core.data.remote.response.MovieResponse
import id.bangkit2021.submissionekspert.core.utils.Constanta
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovieData(
        @Query("api_key") apiKey: String = Constanta.API_KEY,
        @Query("language") language: String= Constanta.EN_US,
        @Query("page") page: Int = 1
    ): MovieResponse
}