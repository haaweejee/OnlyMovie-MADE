package id.bangkit2021.submissionekspert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.bangkit2021.submissionekspert.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movieFavorite = movieUseCase.getFavoriteMovie().asLiveData()
}