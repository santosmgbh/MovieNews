package com.arctouch.codechallenge.repository.webservice

import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.dto.GenreDTO
import com.arctouch.codechallenge.model.dto.MovieDTO
import com.arctouch.codechallenge.model.dto.MovieDetailDTO
import com.arctouch.codechallenge.model.dto.UpcomingMoviesDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieService {

    private val api: TmdbApi = RetrofitConfig().tmdbApi

    fun getMovie(id: Long, success: (movies: List<Movie>) -> Unit) {
        api.movie(id, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .enqueue(object : Callback<MovieDetailDTO> {
                    override fun onResponse(call: Call<MovieDetailDTO>, response: Response<MovieDetailDTO>) {
                        var movieListDTO: List<MovieDTO> = response.body()?.results ?: listOf()
                        var movieList: List<Movie> = getListFromDTO(movieListDTO)
                        success(movieList)
                    }

                    override fun onFailure(call: Call<MovieDetailDTO>, t: Throwable) {

                    }

                })
    }

    fun getUpcomingMovies(success: (movies: List<Movie>) -> Unit) {
        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
                .enqueue(object : Callback<UpcomingMoviesDTO> {

                    override fun onResponse(call: Call<UpcomingMoviesDTO>, response: Response<UpcomingMoviesDTO>) {
                        var movieListDTO: List<MovieDTO> = response.body()?.results ?: listOf()
                        var movieList: List<Movie> = getListFromDTO(movieListDTO)
                        success(movieList)
                    }

                    override fun onFailure(call: Call<UpcomingMoviesDTO>, t: Throwable) {

                    }

                })
    }

    private fun getListFromDTO(movieListDTO: List<MovieDTO>) =
            movieListDTO.map { movieDTO -> Movie(movieDTO.id, movieDTO.title, movieDTO.overview, null, movieDTO.genreIds, movieDTO.posterPath, movieDTO.backdropPath, movieDTO.releaseDate) }

    fun getGenres(success: (genres: List<Genre>) -> Unit) {
        api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .enqueue(object : Callback<GenreDTO> {

                    override fun onResponse(call: Call<GenreDTO>, response: Response<GenreDTO>) {
                        var genresList: List<Genre> = response.body()?.genres ?: listOf()
                        success(genresList)
                    }

                    override fun onFailure(call: Call<GenreDTO>, t: Throwable) {

                    }

                })
    }

}