package com.mvlprem.freebie.api

import com.mvlprem.freebie.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * generates an implementation of the GamesApi interface.
 */
class RetrofitInstance {
    companion object {

        private val moshi by lazy {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        private val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
        }

        val api: GamesApi by lazy {
            retrofit.create(GamesApi::class.java)
        }
    }
}