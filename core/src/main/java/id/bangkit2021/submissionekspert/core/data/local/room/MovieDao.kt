package id.bangkit2021.submissionekspert.core.data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    //Movie
    @Query("SELECT * FROM movie")
    fun getMovieList(): Flow<List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>>

    @Query("SELECT * FROM movie where favorite = 1")
    fun getFavoriteMovie(): Flow<List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntities: List<id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity>)

    @Update
    fun updateMovies(movieEntity: id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity)

}