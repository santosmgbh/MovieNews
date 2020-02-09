package com.arctouch.codechallenge.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class DetailActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val name: ObservableField<String> = ObservableField("")
    val overview: ObservableField<String> = ObservableField("")
    val releaseDate: ObservableField<String> = ObservableField("")


    fun loadMovie(id: Long): LiveData<Movie> {
        return movieRepository.getMovie(id)
    }
}
