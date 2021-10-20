package com.mvlprem.freebie.api

import com.mvlprem.freebie.model.Games
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Returns list of Games.
 */
interface GamesApi {
    @GET("giveaways")
    suspend fun getGames(
        @Query("platform") type: String?
    ): Response<List<Games>>
}