package com.mvlprem.freebie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mvlprem.freebie.R
import com.mvlprem.freebie.databinding.ActivityFreebieBinding

/**
 * Activity is just for hosting fragments &
 * Providing bottom navigation
 */
class FreebieActivity : AppCompatActivity() {

    /**
     * Fields
     */
    private lateinit var binding: ActivityFreebieBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Inflating the layout
         */
        binding = ActivityFreebieBinding.inflate(layoutInflater)

        /**
         * Changing Back To Normal Theme From Splash Screen Theme
         */
        setTheme(R.style.Theme_Freebie)

        setContentView(binding.root)

        /**
         * Initializing Fields
         */
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigation

        /**
         * Setting up Bottom Navigation With NavController
         */
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemReselectedListener { }
    }
}