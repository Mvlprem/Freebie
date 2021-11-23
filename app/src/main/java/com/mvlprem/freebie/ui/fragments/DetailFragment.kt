package com.mvlprem.freebie.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.FragmentDetailBinding
import com.mvlprem.freebie.model.Games
import com.mvlprem.freebie.ui.SharedViewModel

/**
 * Displays detailed information about a selected piece of [Games].
 * which it gets as a Parcelable property through Jetpack Navigation's SafeArgs.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var game: Games
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)

        /**
         * Gets selected [game] through Navigation's SafeArgs
         */
        game = DetailFragmentArgs.fromBundle(requireArguments()).game
        /**
         * Passing safeargs [game] to [sharedViewModel]
         */
        sharedViewModel.fragmentArgs(game)

        binding.viewModel = sharedViewModel

        /**
         * Click listener for { viewGiveaway } button in { fragment_detail.xml }
         * opens displayed [game] in available web browser using intent
         */
        binding.viewGiveaway.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(game.openGiveaway)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }


        return binding.root
    }
}