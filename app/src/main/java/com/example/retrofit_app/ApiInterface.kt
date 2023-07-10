package com.example.retrofit_app

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("Shakespeare;Sonnet")
    fun getData(): Call<List<PoetryApiDataItem>>

}