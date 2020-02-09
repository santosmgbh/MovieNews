package com.arctouch.codechallenge.repository

import androidx.lifecycle.LiveData
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.dto.UpcomingMoviesDTO

interface MovieRepository {

    fun getMovie(id: Long): LiveData<Movie>

    fun getUpcomingMovies(page: Long, success: (movies: UpcomingMoviesDTO) -> Unit)

    fun getGenres(): LiveData<List<Genre>>
}