package id.bangkit2021.submissionekspert.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("original_title")
    var original_title: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
)
