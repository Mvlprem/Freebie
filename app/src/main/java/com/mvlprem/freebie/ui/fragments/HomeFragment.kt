package com.mvlprem.freebie.ui.fragments

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
import com.mvlprem.freebie.model.Games
import com.mvlprem.freebie.ui.SharedViewModel

/**
 * Displays list of [Games]
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        /**
         * Adapter for { recyclerview } with clickHandler lambda that
         * tells when our [Games] item is clicked and
         * navigates to a [DetailFragment] with selected [Games] data as parcelable
         */
        val adapter = RecyclerAdapter(GameItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        })
        /**
         * Sets the adapter of recyclerview
         */
        binding.recyclerview.adapter = adapter
        /**
         * Observing response and submitting List [Games] to adapter
         */
        viewModel.response.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        /**
         * The contextView is used to make snackBar
         */
        val contextView = binding.recyclerview
        /**
         * Observing the networkState and
         * showing a snackBar if not true
         */
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
         * observing responseError, on true
         * hiding recyclerview and showing another layout with error message
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