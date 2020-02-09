package com.arctouch.codechallenge.ui.home

import android.view.View.VISIBLE
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class HomeActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val progressVisible = ObservableField<Int>(VISIBLE)

    fun showUpcomingVideos(): LiveData<List<Movie>> {
        return movieRepository.getUpcomingVideos()
    }
}