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
import com.mvlprem.freebie.model.Games
import com.mvlprem.freebie.ui.SharedViewModel
import com.mvlprem.freebie.util.Constants.EPIC
import com.mvlprem.freebie.util.Constants.ORIGIN
import com.mvlprem.freebie.util.Constants.PLAY_STATION
import com.mvlprem.freebie.util.Constants.STEAM
import com.mvlprem.freebie.util.Constants.UBISOFT
import com.mvlprem.freebie.util.Constants.XBOX

/**
 * [MenuFragment] as BottomSheetDialog
 * Displays available retrofit query values
 */
class MenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_menu, container, false)

        /**
         * Checked listener for chips in { fragment_menu.xml }
         * when checked passes query value to
         * { apiQuery() } function in [sharedViewModel]
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
            sharedViewModel.apiQuery(filter)
        }


        return binding.root
    }
}