package com.arctouch.codechallenge.util

import com.arctouch.codechallenge.repository.webservice.TmdbApi

private val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private val BACKDROP_URL = "https://image.tmdb.org/t/p/w780/shDFE0i7josMt9IKXdYpnMFFgNV.jpg?api_key=1f54bd990f1cdfb230adb312546d765d"

class MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + TmdbApi.API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + TmdbApi.API_KEY
    }
}
