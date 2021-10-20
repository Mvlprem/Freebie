package com.mvlprem.freebie.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mvlprem.freebie.R
import com.mvlprem.freebie.adapters.GameItemClickListener
import com.mvlprem.freebie.adapters.RecyclerAdapter
import com.mvlprem.freebie.databinding.FragmentHomeBinding
import com.mvlprem.freebie.ui.SharedViewModel
import com.mvlprem.freebie.ui.ViewModelFactory

class HomeFragment : Fragment() {

    /**
     * Fields
     */
    private lateinit var binding: FragmentHomeBinding
    private lateinit var application: Application
    private val viewModel: SharedViewModel by activityViewModels {
        ViewModelFactory(
            null,
            application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /**
         * Inflate the layout for this fragment
         */
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        /**
         * Initializing Fields
         */
        application = requireNotNull(this.activity).application

        /**
         * Attaching adapter to recycler and passing
         * retrieved api data to adapter by observing the api response
         * onclick navigates to a detail fragment with item data using parcelable
         */
        val adapter = RecyclerAdapter(GameItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        })
        binding.recyclerview.adapter = adapter
        viewModel.response.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        /**
         * The contextView used to make the snackBar
         * Observing the user network state and
         * showing a snackBar if user doesn't have a network connection
         */
        val contextView = binding.recyclerview
        viewModel.networkState.observe(viewLifecycleOwner, {
            if (!it)
                Snackbar.make(
                    contextView,
                    getString(R.string.networkError),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Retry") { viewModel.apiQuery(null) }
                    .show()
        })

        /**
         * Handling response Errors
         * If there is no data from api
         * hiding recyclerview and showing layout with error msg
         */
        viewModel.responseError.observe(viewLifecycleOwner, {
            binding.apply {
                if (it) {
                    recyclerview.visibility = View.GONE
                    linearLayout.visibility = View.VISIBLE
                } else {
                    recyclerview.visibility = View.VISIBLE
                    linearLayout.visibility = View.GONE
                }
            }
        })

        return binding.root
    }
}