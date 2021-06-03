package id.bangkit2021.submissionekspert.core.domain.usecase

import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getMovieList() = movieRepository.getMovieList()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, favorite: Boolean) =
        movieRepository.setFavoriteMovie(movie, favorite)


}