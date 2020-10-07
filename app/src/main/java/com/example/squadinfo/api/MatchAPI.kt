package com.example.squadinfo.api

import com.google.gson.JsonElement
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface MatchAPI {

    //get simple json object response
    @GET("sapk01222019186652.json")
    suspend fun getMatchData(): Response<JsonElement>

}
