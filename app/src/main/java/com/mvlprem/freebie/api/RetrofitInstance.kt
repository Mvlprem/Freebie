package com.mvlprem.freebie.api

import com.mvlprem.freebie.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Main entry point for network access. Call like `RetrofitInstance.api.getGames()`
 */
class RetrofitInstance {
    companion object {

        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility.
         */
        private val moshi by lazy {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        /**
         * Configure retrofit to parse JSON
         */
        private val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
        }

        /**
         * Generates an implementation of the GamesApi interface.
         */
        val api: GamesApi by lazy {
            retrofit.create(GamesApi::class.java)
        }
    }
}