package id.bangkit2021.submissionekspert.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<MovieModel>
)
