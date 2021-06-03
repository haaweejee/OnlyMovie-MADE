package id.bangkit2021.submissionekspert.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [id.bangkit2021.submissionekspert.core.data.local.entity.MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): id.bangkit2021.submissionekspert.core.data.local.room.MovieDao

}