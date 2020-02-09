package com.arctouch.codechallenge.model.dto

import com.squareup.moshi.Json

data class UpcomingMoviesDTO(
        val page: Int,
        val results: List<MovieDTO>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)