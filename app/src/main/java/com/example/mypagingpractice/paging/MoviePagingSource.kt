package com.example.mypagingpractice.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mypagingpractice.models.Movie
import com.example.mypagingpractice.retrofit.MovieAPI
import com.example.mypagingpractice.utils.Constants
import java.lang.Exception

class MoviePagingSource(val movieAPI: MovieAPI) : PagingSource<Int,Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
        if(state.anchorPosition!=null){
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
            if(anchorPage?.prevKey != null){
                return anchorPage.prevKey!!.plus(1)
            }else if (anchorPage?.nextKey != null){
                return anchorPage.prevKey!!.minus(1)
            }else{
                return null;
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: 1
            val response = movieAPI.getAllMovies("Lucifer", position, Constants.API_KEY)
            return LoadResult.Page(
                data = response.Search,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.Search.isEmpty()) null else position + 1
            )
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}