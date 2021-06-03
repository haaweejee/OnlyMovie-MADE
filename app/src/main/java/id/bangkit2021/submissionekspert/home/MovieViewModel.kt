package id.bangkit2021.submissionekspert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.bangkit2021.submissionekspert.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getMovieList().asLiveData()
}