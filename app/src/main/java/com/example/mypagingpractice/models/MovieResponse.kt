package com.example.mypagingpractice.models


data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)