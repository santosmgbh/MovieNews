package com.arctouch.codechallenge.ui.home

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class HomeActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun showUpcomingVideos(success: (movies: List<Movie>) -> Unit) {
        movieRepository.getUpcomingVideos(success)
    }
}