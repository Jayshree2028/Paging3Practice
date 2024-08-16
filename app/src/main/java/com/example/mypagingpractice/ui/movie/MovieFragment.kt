package com.example.mypagingpractice.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypagingpractice.R
import com.example.mypagingpractice.databinding.FragmentDetailsBinding
import com.example.mypagingpractice.databinding.FragmentMovieBinding
import com.example.mypagingpractice.paging.MoviePagingnationAdapter
import com.example.mypagingpractice.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var movieAdapter: MoviePagingnationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        movieAdapter = MoviePagingnationAdapter(requireContext(), mainViewModel)
        setRecyclerView()
        mainViewModel.responseData.observe(requireActivity(), {
            movieAdapter.submitData(lifecycle, it)
        })

        movieAdapter.onMovieClick {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            findNavController().navigate(action)

        }

    }

    private fun setRecyclerView() {
        binding.rvMovieData.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

}