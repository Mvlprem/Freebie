package com.mvlprem.freebie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.FragmentSettingsBinding
import com.mvlprem.freebie.ui.SharedViewModel

/**
 * Displays theme selection text
 */
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    /**
     * Default selected theme item
     */
    private var selectedItem = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_settings, container, false)

        /**
         * Gets stored theme value in DataStore from [sharedViewModel]
         * by calling observe on { readFromDataStore } and
         * sets it to [selectedItem]
         */
        sharedViewModel.readFromDataStore.observe(viewLifecycleOwner, {
            selectedItem = it
        })

        /**
         * click Listener for "theme" text in { fragment_Settings.xml }
         * calls [materialDialogue] on click
         */
        binding.theme.setOnClickListener {
            materialDialogue()
        }


        return binding.root
    }

    /**
     * Opens up material dialogue with single choice theme items to choose from and
     * stores selected item in DataStore by calling { saveToDataStore() } in [sharedViewModel]
     */
    private fun materialDialogue() {
        val singleItem = arrayOf("Light", "Dark", "System default")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialogue_title)
            .setSingleChoiceItems(singleItem, selectedItem) { dialog, which ->
                val item: Int = when (which) {
                    0 -> which
                    1 -> which
                    else -> {
                        2
                    }
                }
                sharedViewModel.saveToDataStore(item)
                dialog.dismiss()
            }.show()
    }

}