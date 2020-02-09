package com.arctouch.codechallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.data.MovieDao
import com.arctouch.codechallenge.repository.webservice.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieRepositoryImpl : MovieRepository, KoinComponent {

    private val database: MovieDao by inject()
    private val movieService by lazy {
        MovieService()
    }

    override fun getUpcomingVideos(): LiveData<List<Movie>> {
        val response = MutableLiveData<List<Movie>>()
        movieService.getUpcomingMovies {
            getGenres { genres ->
                val moviesWithGenres = fillGenresInMovies(it, genres)
                response.value = moviesWithGenres
            }

        }
        return response
    }

    private fun fillGenresInMovies(it: List<Movie>, cachedGenres: List<Genre>): List<Movie> {
        return it.map { movie ->
            movie.copy(genres = cachedGenres.filter { movie.genreIds?.contains(it.id) == true })
        }
    }


    override fun getGenres(): LiveData<List<Genre>> {
        val response = MutableLiveData<List<Genre>>()

        getGenres {
            response.value = it
        }
        return response
    }

    private fun getGenres(success: (genres: List<Genre>) -> Unit) {
        GlobalScope.launch {
            val cachedGenres = database.getGenres()

            if (cachedGenres.isNullOrEmpty()) {
                movieService.getGenres { genres ->
                    GlobalScope.launch {
                        database.insertAll(genres)
                    }
                    GlobalScope.launch(context = Dispatchers.Main) {
                        success(genres)
                    }

                }
            } else {
                GlobalScope.launch(context = Dispatchers.Main) {
                    success(cachedGenres)
                }
            }
        }

    }
}