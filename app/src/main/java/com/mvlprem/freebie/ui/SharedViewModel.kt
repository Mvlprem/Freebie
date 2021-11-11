package com.mvlprem.freebie.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvlprem.freebie.FreebieApplication
import com.mvlprem.freebie.api.RetrofitInstance
import com.mvlprem.freebie.model.Games
import kotlinx.coroutines.launch

class SharedViewModel(games: Games?, application: Application) : AndroidViewModel(application) {

    /**
     * Data coming through navigation to
     * Detail fragment
     */
    private val gameData = MutableLiveData<Games>(games)
    val game: LiveData<Games> = gameData

    /**
     * api response data
     */
    private val apiResponse = MutableLiveData<List<Games>>()
    val response: LiveData<List<Games>> = apiResponse

    /**
     * api response Error
     */
    private val apiResponseError = MutableLiveData<Boolean>()
    val responseError: LiveData<Boolean> = apiResponseError

    /**
     * user network results
     */
    private val userNetworkState = MutableLiveData<Boolean>()
    val networkState: LiveData<Boolean> = userNetworkState

    init {
        apiQuery(null)
    }

    /**
     * Network Check before performing api call
     *
     */
    fun apiQuery(filter: String?) {
        if (isNetworkConnected()) {
            userNetworkState.value = true
            apiCall(filter)
        } else {
            userNetworkState.value = false
        }
    }

    /**
     * Performing Api Call & Retrieving data
     * Takes in a query param which is null by default
     */
    private fun apiCall(platform: String?) {
        viewModelScope.launch {
            try {
                apiResponse.value = RetrofitInstance.api.getGames(platform)
                apiResponseError.value = false
            } catch (e: Exception) {
                apiResponse.value = ArrayList()
                apiResponseError.value = true
            }
        }
    }

    /**
     * Getting network details
     */
    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getApplication<FreebieApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }
}