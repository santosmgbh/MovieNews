package com.arctouch.codechallenge

import com.arctouch.codechallenge.repository.MovieRepository
import com.arctouch.codechallenge.repository.MovieRepositoryImpl
import com.arctouch.codechallenge.ui.home.HomeActivityViewModel
import com.arctouch.codechallenge.ui.splash.SplashActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule =
        module {
            single<MovieRepository> { MovieRepositoryImpl() }

            viewModel { SplashActivityViewModel(get()) }
            viewModel { HomeActivityViewModel(get()) }
        }
