package com.example.mypagingpractice.paging

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mypagingpractice.BR
import com.example.mypagingpractice.databinding.MovieItemsBinding
import com.example.mypagingpractice.models.Movie
import com.example.mypagingpractice.viewmodels.MainViewModel


class MoviePagingnationAdapter (private val context: Context,private val mainViewModel: MainViewModel):
    PagingDataAdapter<Movie, MoviePagingnationAdapter.MovieViewHolder>(COMPARATOR) {

    var onClick: ((String)-> Unit)? = null

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
    fun onMovieClick(listener: (String)-> Unit){
        onClick = listener
    }
    class MovieViewHolder(val movieItemsBinding: MovieItemsBinding) :
        RecyclerView.ViewHolder(movieItemsBinding.root) {


    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
           /* holder.movieItemsBinding.movieTitle.text = item.Title
            holder.movieItemsBinding.movieYear.text = item.Year
            Glide.with(holder.itemView.context).load(item.Poster)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieItemsBinding.movieImage)*/
            holder.movieItemsBinding.setVariable(BR.movie,item)
        }

        holder.movieItemsBinding.root.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: " + mainViewModel.fetchMovieDetails(item?.imdbID!!))
            onClick?.let {
                it(item.imdbID)
            }

            /* val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("IMDB_ID", item.imdbID)
            context.startActivity(intent)*/
            /* val fragment : Fragment = DetailsFragment().newInstance()
             val transaction : FragmentTransaction = (context as (FragmentActivity)).supportFragmentManager.beginTransaction()
             transaction.replace(com.example.paging3library.R.id.fragment_container1,fragment,"main_fragment")
             transaction.commit()*/
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)

    }

}