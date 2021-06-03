package id.bangkit2021.submissionekspert.detail

import androidx.lifecycle.ViewModel
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, favorite: Boolean) =
        movieUseCase.setFavoriteMovie(movie, favorite)

}