package com.mvlprem.freebie.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.mvlprem.freebie.FreebieApplication
import com.mvlprem.freebie.api.RetrofitInstance
import com.mvlprem.freebie.data.DataStore
import com.mvlprem.freebie.model.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Creating DataStore by passing { application } as context
     */
    private val datastore = DataStore(application)

    /**
     * Gets the value stored in [DataStore] in the form of Flow<Int> and
     * converting as [LiveData] by calling { asLiveData() } on it so it
     * can be observed.
     */
    val readFromDataStore = datastore.read.asLiveData()

    /**
     * Stores user selected theme in DataStore by calling [DataStore.save]
     * since it's a suspend function it needs to be called in coroutine scope
     * @param value selected theme
     */
    fun saveToDataStore(value: Int) = viewModelScope.launch(Dispatchers.IO) {
        datastore.save(value)
    }

    /**
     * stores the [Games] [List] from { DetailFragmentArgs }
     * MutableLiveData, because we will be updating the List with new values
     */
    private val gameData = MutableLiveData<Games>()
    val game: LiveData<Games> = gameData

    /**
     * stores the [Games] [List] from retrofit service
     * MutableLiveData, because we will be updating the List with new values
     */
    private val apiResponse = MutableLiveData<List<Games>>()
    val response: LiveData<List<Games>> = apiResponse

    /**
     * stores the status of api response error
     */
    private val apiResponseError = MutableLiveData<Boolean>()
    val responseError: LiveData<Boolean> = apiResponseError

    /**
     * stores the status of user network connectivity
     */
    private val userNetworkState = MutableLiveData<Boolean>()
    val networkState: LiveData<Boolean> = userNetworkState

    /**
     * Calling { apiQuery() } on init so we can display immediately
     */
    init {
        apiQuery(null)
    }

    /**
     * Gets network connectivity status from { isNetworkConnected() } and
     * calls [apiQuery] if network found
     * updates [userNetworkState] [Boolean]
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
     * Gets information from the { DetailFragmentArgs } and
     * updates the [gameData] [List]
     * @param game game that has been clicked in recycler list.
     */
    fun fragmentArgs(game: Games) {
        gameData.value = game
    }

    /**
     * Gets filtered [Games] information from the API Retrofit service
     * updates the [apiResponse] [List] and [apiResponseError] [Boolean]
     * @param platform sent as part of the web server request, null by default
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
     * Checks user's network connectivity when called
     * need ACCESS_NETWORK_STATE permission in Manifest
     * @return Boolean
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