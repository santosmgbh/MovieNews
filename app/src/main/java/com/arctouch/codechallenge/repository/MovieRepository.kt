package com.arctouch.codechallenge.repository

import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie

interface MovieRepository {

    fun getUpcomingVideos(success: (movies: List<Movie>) -> Unit)

    fun getGenres(success: (genres: List<Genre>) -> Unit)
}