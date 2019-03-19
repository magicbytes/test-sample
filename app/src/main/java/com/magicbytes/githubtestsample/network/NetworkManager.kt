package com.magicbytes.githubtestsample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    fun service(): NetworkService {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(NetworkService::class.java)
    }
}