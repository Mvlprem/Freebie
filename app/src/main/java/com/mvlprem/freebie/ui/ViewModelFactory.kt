package com.mvlprem.freebie.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvlprem.freebie.model.Games

/**
 * Instantiating ViewModels with required params
 */
class ViewModelFactory(private val games: Games?, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(games, application) as T
        }
        throw IllegalArgumentException("ViewModelProvider Error")
    }
}