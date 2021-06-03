package id.bangkit2021.submissionekspert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie (
    var id: Int,
    var poster_path : String,
    var original_title: String,
    var release_date: String,
    var overview: String,
    var backdrop_path: String,
    var favorite: Boolean
    ):Parcelable