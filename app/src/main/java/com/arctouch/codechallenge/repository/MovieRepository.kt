package com.arctouch.codechallenge.repository

import androidx.lifecycle.LiveData
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie

interface MovieRepository {

    fun getUpcomingVideos(): LiveData<List<Movie>>

    fun getGenres(): LiveData<List<Genre>>
}