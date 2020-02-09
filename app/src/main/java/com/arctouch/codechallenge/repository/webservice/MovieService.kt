package com.arctouch.codechallenge.repository.webservice


import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.dto.GenreDTO
import com.arctouch.codechallenge.model.dto.UpcomingMoviesDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieService {

    private val api: TmdbApi = RetrofitConfig().tmdbApi

    fun getMovie(id: Long, success: (movie: Movie) -> Unit) {
        api.movie(id, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        var movie = response.body()
                        success(movie!!)
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {

                    }

                })
    }

    fun getUpcomingMovies(page: Long, success: (movies: UpcomingMoviesDTO) -> Unit) {
        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page)
                .enqueue(object : Callback<UpcomingMoviesDTO> {

                    override fun onResponse(call: Call<UpcomingMoviesDTO>, response: Response<UpcomingMoviesDTO>) {
                        var movieListDTO: List<Movie> = response.body()?.results ?: listOf()
                        var movieList: List<Movie> = getListFromDTO(movieListDTO)

                        response.body()?.let {
                            success(it)
                        }

                    }

                    override fun onFailure(call: Call<UpcomingMoviesDTO>, t: Throwable) {

                    }

                })
    }

    private fun getListFromDTO(movieList: List<Movie>) =
            movieList.map { movieDTO -> Movie(movieDTO.id, movieDTO.title, movieDTO.overview, null, movieDTO.genreIds, movieDTO.posterPath, movieDTO.backdropPath, movieDTO.releaseDate) }

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