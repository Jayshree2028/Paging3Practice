package com.example.mypagingpractice.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mypagingpractice.R
import com.example.mypagingpractice.databinding.FragmentDetailsBinding
import com.example.mypagingpractice.utils.Status

import com.example.mypagingpractice.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val arguments : DetailsFragmentArgs by navArgs()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }
        mainViewModel.fetchMovieDetails(arguments.imdbId!!)
        mainViewModel.movieDetails.observe(requireActivity()){movieDetails->
            binding.movieDetails = movieDetails
        }


        /*mainViewModel.movieDetails.observe(viewLifecycleOwner) {
            when (it.Response) {
                Status.LOADING.toString() -> {
                    binding.detailsProgress.visibility = View.VISIBLE
                }
                Status.ERROR.toString() -> {
                    binding.detailsProgress.visibility = View.GONE
                    // Optionally handle the error case here
                }
                Status.SUCCESS.toString() -> {
                    binding.detailsProgress.visibility = View.GONE
                    binding.movieDetails = it.peekContent().data
                }

            }
        }*/

    }

}