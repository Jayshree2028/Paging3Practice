package com.example.mypagingpractice.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.mypagingpractice.models.moviedetails.MovieDetails
import com.example.mypagingpractice.paging.MoviePagingSource
import com.example.mypagingpractice.retrofit.MovieAPI
import com.example.mypagingpractice.utils.Constants
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieAPI: MovieAPI) {
    fun getMovies() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { MoviePagingSource(movieAPI) }
    ).liveData

    suspend fun getMovieDetails(imdbID: String): MovieDetails {
        val movieDetails = movieAPI.getMovieDetails(imdbID, Constants.API_KEY)
        return movieDetails
    }
}