package com.example.myapplicationtv.ui.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteServices {

    @GET("discover/movie?sort_by=popularity.desc")
     suspend fun listPopularMovies(
        @Query("api_key")apiKey:String,
        @Query("sort_by")sortBy:String,
        @Query("vote_count.gte")voteCount:Int=100):Results


}