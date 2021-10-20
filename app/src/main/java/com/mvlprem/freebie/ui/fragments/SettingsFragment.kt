package com.mvlprem.freebie.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    // Fields
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_settings, container, false)

        /**
         * Updating button checked state from sharedPrefs
         */
        binding.apply {
            btnDark.isChecked = updateSharedPrefs("btn_dark")
            btnLight.isChecked = updateSharedPrefs("btn_light")
            btnDefault.isChecked = updateSharedPrefs("btn_default")
        }

        /**
         * Button onclick listener for updating theme
         * Storing btn checked state in sharedPrefs
         */
        binding.btnTheme.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (group.findViewById<Button>(checkedId)) {
                btn_dark -> {
                    setDefaultNightMode(MODE_NIGHT_YES)
                    sharedPrefs("btn_dark", isChecked)
                }
                btn_light -> {
                    setDefaultNightMode(MODE_NIGHT_NO)
                    sharedPrefs("btn_light", isChecked)
                }
                btn_default -> {
                    setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
                    sharedPrefs("btn_default", isChecked)
                }
            }
        }

        return binding.root
    }

    /**
     * Storing btn checked state in sharedPrefs
     * this fun is called when button is checked and updates the value
     */
    private fun sharedPrefs(key: String, value: Boolean) {
        val sharedPreferences = requireActivity().getSharedPreferences("BTN_STATE", MODE_PRIVATE)
        sharedPreferences.edit()
            .putBoolean(key, value)
            .apply()
    }

    /**
     * Returns btn state value
     */
    private fun updateSharedPrefs(key: String): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("BTN_STATE", MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, true)
    }
}