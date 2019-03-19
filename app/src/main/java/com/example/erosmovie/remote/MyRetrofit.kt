package com.example.erosmovie.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MyRetrofit {
    private val retrofit:Retrofit? = null
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    val retrofitinstance:Retrofit?
         get(){
             if (retrofit == null) {
                 val gson = GsonBuilder().setLenient().create()
                 val retrofit = retrofit2.Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .build()
                 return retrofit
             }
             return retrofit
         }

    fun getAPIService(): APIService? {
       return retrofitinstance?.create(APIService::class.java)
    }
}