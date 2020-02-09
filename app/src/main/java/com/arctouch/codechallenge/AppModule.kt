package com.arctouch.codechallenge

import androidx.room.Room
import com.arctouch.codechallenge.repository.MovieRepository
import com.arctouch.codechallenge.repository.MovieRepositoryImpl
import com.arctouch.codechallenge.repository.data.AppDatabase
import com.arctouch.codechallenge.ui.detail.DetailActivityViewModel
import com.arctouch.codechallenge.ui.home.HomeActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule =
        module {
            single {
                Room.databaseBuilder(
                        androidApplication(),
                        AppDatabase::class.java, "movieNewsDb"
                ).build()
            }
            single { get<AppDatabase>().movieDao() }
            single<MovieRepository> { MovieRepositoryImpl() }

            viewModel { HomeActivityViewModel(get()) }
            viewModel { DetailActivityViewModel(get()) }
        }
