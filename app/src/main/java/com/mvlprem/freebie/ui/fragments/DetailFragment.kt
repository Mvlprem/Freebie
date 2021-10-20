package com.mvlprem.freebie.ui.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.FragmentDetailBinding
import com.mvlprem.freebie.model.Games
import com.mvlprem.freebie.ui.SharedViewModel
import com.mvlprem.freebie.ui.ViewModelFactory

class DetailFragment : Fragment() {

    /**
     * Fields
     */
    private lateinit var binding: FragmentDetailBinding
    private lateinit var application: Application
    private lateinit var game: Games
    private val viewModel: SharedViewModel by viewModels {
        ViewModelFactory(
            game,
            application
        )
    }


    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        application = requireNotNull(this.activity).application
        game = DetailFragmentArgs.fromBundle(requireArguments()).game

        /**
         * bindind variable to viewModel
         */
        binding.viewModel = viewModel

        /**
         * view_giveaway button onclick listener
         * opens giveaway links in available web browser
         */
        binding.viewButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(viewModel.game.value?.openGiveaway)
            if (intent.resolveActivity(application.packageManager) != null) {
                startActivity(intent)
            }
        }


        return binding.root
    }
}