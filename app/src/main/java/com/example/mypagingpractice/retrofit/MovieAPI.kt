package com.example.mypagingpractice.retrofit

import com.example.mypagingpractice.models.MovieResponse
import com.example.mypagingpractice.models.moviedetails.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ):MovieResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String
    ):MovieDetails
}