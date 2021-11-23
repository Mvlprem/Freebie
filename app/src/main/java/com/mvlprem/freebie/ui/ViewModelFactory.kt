package com.mvlprem.freebie.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for constructing SharedViewModel with params
 */
class ViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(application) as T
        }
        throw IllegalArgumentException("ViewModelProvider Error")
    }
}