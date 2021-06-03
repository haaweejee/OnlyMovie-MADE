package id.bangkit2021.submissionekspert.core.di

import androidx.room.Room
import id.bangkit2021.submissionekspert.core.data.MovieRepository
import id.bangkit2021.submissionekspert.core.data.local.LocalDataSource
import id.bangkit2021.submissionekspert.core.data.local.room.MovieDatabase
import id.bangkit2021.submissionekspert.core.data.remote.RemoteDataSource
import id.bangkit2021.submissionekspert.core.data.remote.api.ApiService
import id.bangkit2021.submissionekspert.core.domain.repository.IMovieRepository
import id.bangkit2021.submissionekspert.core.utils.AppExecutors
import id.bangkit2021.submissionekspert.core.utils.Constanta
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CoreModule {
    val databaseModule = module {
        factory { get<MovieDatabase>().movieDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                MovieDatabase::class.java, "Movie.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constanta.BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }
    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IMovieRepository> {
            MovieRepository(
                get(),
                get(),
                get()
            )
        }
    }

}