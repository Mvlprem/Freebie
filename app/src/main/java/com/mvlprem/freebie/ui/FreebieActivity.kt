package com.mvlprem.freebie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.ActivityFreebieBinding
import com.mvlprem.freebie.util.userPreferredTheme

/**
 * This is a single activity application that uses the Navigation library.
 * Content is displayed by Fragments.
 */
class FreebieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreebieBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private val sharedViewModel: SharedViewModel by viewModels { ViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Observing readFromDataStore and changing theme
         * by passing the value to function [userPreferredTheme] in Util file
         */
        sharedViewModel.readFromDataStore.observe(this, { userPreferredTheme(it) })

        binding = ActivityFreebieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Bottom navigation setup with [navController]
         */
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemReselectedListener { }
    }
}