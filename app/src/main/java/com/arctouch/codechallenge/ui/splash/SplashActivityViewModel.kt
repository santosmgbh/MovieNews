package com.arctouch.codechallenge.ui.splash

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.repository.MovieRepository

class SplashActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun cacheGenres(success: (() -> Unit)) {
        movieRepository.getGenres {
            success()
        }
    }
}