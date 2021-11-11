package com.mvlprem.freebie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.FragmentMenuBinding
import com.mvlprem.freebie.ui.SharedViewModel
import com.mvlprem.freebie.util.Constants.EPIC
import com.mvlprem.freebie.util.Constants.ORIGIN
import com.mvlprem.freebie.util.Constants.PLAY_STATION
import com.mvlprem.freebie.util.Constants.STEAM
import com.mvlprem.freebie.util.Constants.UBISOFT
import com.mvlprem.freebie.util.Constants.XBOX

class MenuFragment : BottomSheetDialogFragment() {

    /**
     * Fields
     */
    private lateinit var binding: FragmentMenuBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * Inflate the layout for this fragment
         */
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_menu, container, false)

        /**
         * Checked listener for chips
         * when checked passing query param to the
         * apiQuery fun in sharedViewModel
         */
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val filter = when (group.findViewById<Chip>(checkedId)) {
                binding.steamChip -> STEAM
                binding.ubisoftChip -> UBISOFT
                binding.originChip -> ORIGIN
                binding.epicChip -> EPIC
                binding.playstationChip -> PLAY_STATION
                binding.xboxChip -> XBOX
                else -> null
            }
            viewModel.apiQuery(filter)
        }


        return binding.root
    }
}