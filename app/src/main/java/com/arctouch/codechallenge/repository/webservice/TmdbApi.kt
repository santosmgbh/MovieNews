package com.arctouch.codechallenge.repository.webservice

import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.dto.GenreDTO
import com.arctouch.codechallenge.model.dto.UpcomingMoviesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    companion object {
        const val URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "1f54bd990f1cdfb230adb312546d765d"
        const val DEFAULT_LANGUAGE = "pt-BR"
        const val DEFAULT_REGION = "BR"
    }

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenreDTO>

    @GET("movie/upcoming")
    fun upcomingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Long
    ): Call<UpcomingMoviesDTO>

    @GET("movie/{id}")
    fun movie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>
}
