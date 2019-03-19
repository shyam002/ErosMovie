package com.example.erosmovie.remote

import com.example.erosmovie.model.ErosMovie
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



public interface APIService {
    @GET("top_rated")
    fun getAllList( @Query("api_key") api_Key: String,
                    @Query("language") language: String,
                    @Query("page") page: Int = 1): Call<ErosMovie>
}