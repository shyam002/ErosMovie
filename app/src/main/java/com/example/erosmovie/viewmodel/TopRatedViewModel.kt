package com.example.erosmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erosmovie.model.ErosMovie
import com.example.erosmovie.remote.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

const val API_KEY = "1ebea75f8239d533b0408496fbecc94f"

class TopRatedViewModel() : ViewModel() {
    var result = ArrayList<com.example.erosmovie.model.Result>()
    var itemlist  = ArrayList<com.example.erosmovie.model.Result>()
    var mresult = MutableLiveData<ArrayList<com.example.erosmovie.model.Result>>()
    fun getTopRatedMovieList(page: Int){
        val apiService = MyRetrofit.getAPIService()
        val call: Call<ErosMovie>? = apiService?.getAllList(API_KEY, "en-US", page)
        call?.enqueue(object : Callback<ErosMovie> {
            override fun onFailure(call: Call<ErosMovie>, t: Throwable) {
                println("Failed to call Retrofit")
            }

            override fun onResponse(call: Call<ErosMovie>, response: Response<ErosMovie>) {
                try {
                    println("Successfully called = " + response.body()?.results?.size)
                    println(response.body().toString())
                    result = response.body()?.results as ArrayList<com.example.erosmovie.model.Result>
                    for (item in result) {
                        itemlist.add(item)
                    }
                    mresult.value = itemlist
                } catch (e: Exception) {
                }
            }
        })
    }
}