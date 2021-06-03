package id.bangkit2021.submissionekspert.core.utils

import id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity
import id.bangkit2021.submissionekspert.core.data.remote.response.MovieModel
import id.bangkit2021.submissionekspert.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieModel>?) : List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>{
        val movieList = ArrayList<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>()
        input?.map {
            val movie = id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity(
                id = it.id,
                poster_path = it.poster_path,
                original_title = it.original_title,
                release_date = it.release_date,
                overview = it.overview,
                backdrop_path = it.backdrop_path,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapEntitiesToDomain(input: List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>) : List<Movie> =
        input.map{
            Movie(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                release_date = it.release_date,
                overview = it.overview,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity(
            id = input.id,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            favorite = input.favorite
        )

}