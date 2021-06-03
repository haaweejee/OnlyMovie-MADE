package id.bangkit2021.submissionekspert.di

import id.bangkit2021.submissionekspert.core.domain.usecase.MovieInteractor
import id.bangkit2021.submissionekspert.core.domain.usecase.MovieUseCase
import id.bangkit2021.submissionekspert.detail.DetailViewModel
import id.bangkit2021.submissionekspert.home.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AppModule {
    val useCaseModule = module {
        factory<MovieUseCase> { MovieInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { DetailViewModel(get()) }


    }
}