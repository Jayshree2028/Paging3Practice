package com.example.mypagingpractice.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mypagingpractice.models.Movie
import com.example.mypagingpractice.models.moviedetails.MovieDetails
import com.example.mypagingpractice.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel(){
    val responseData : LiveData<PagingData<Movie>> = movieRepository.getMovies().cachedIn(viewModelScope)
    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails
    init {
        viewModelScope.launch {
            movieRepository.getMovies()
        }
    }

    fun fetchMovieDetails(imdbID: String){
        viewModelScope.launch {
            val movieDetails = movieRepository.getMovieDetails(imdbID)
            _movieDetails.value = movieDetails
            Log.d("MainViewModel", "Movie Details: $movieDetails")
        }
    }
}