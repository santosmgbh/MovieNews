package com.arctouch.codechallenge.model.dto

import com.arctouch.codechallenge.model.Genre
import com.squareup.moshi.Json

data class MovieDTO(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: List<Genre>?,
        @Json(name = "genre_ids") val genreIds: List<Int>?,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "backdrop_path") val backdropPath: String?,
        @Json(name = "release_date") val releaseDate: String?
)