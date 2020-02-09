package com.arctouch.codechallenge.model.dto

import com.arctouch.codechallenge.model.Movie
import com.google.gson.annotations.SerializedName

data class UpcomingMoviesDTO(
        val page: Int,
        val results: List<Movie>,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") val totalResults: Int
)