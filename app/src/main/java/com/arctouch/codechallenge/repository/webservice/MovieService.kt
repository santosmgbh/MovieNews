package com.arctouch.codechallenge.repository.webservice

import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieService {

    private val api: TmdbApi = RetrofitConfig().tmdbApi

    fun getMovie(id: Long, success: (movies: List<Movie>) -> Unit) {
//        api.movie(id, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
//                .enqueue(object : Callback<Movie>)
    }

    fun getUpcomingMovies(success: (movies: List<Movie>) -> Unit) {
        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
                .enqueue(object : Callback<UpcomingMoviesResponse> {

                    override fun onResponse(call: Call<UpcomingMoviesResponse>, response: Response<UpcomingMoviesResponse>) {
                        var movieList: List<Movie> = response.body()?.results ?: listOf()
                        success(movieList)
                    }

                    override fun onFailure(call: Call<UpcomingMoviesResponse>, t: Throwable) {

                    }

                })
    }

    fun getGenres(success: (genres: List<Genre>) -> Unit) {
        api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .enqueue(object : Callback<GenreResponse> {

                    override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                        var genresList: List<Genre> = response.body()?.genres ?: listOf()
                        success(genresList)
                    }

                    override fun onFailure(call: Call<GenreResponse>, t: Throwable) {

                    }

                })
    }

}