package id.bangkit2021.submissionekspert.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class FavoriteModule {
    val favoriteModule = module(override = true) {
        viewModel { FavoriteViewModel(get()) }
    }
}