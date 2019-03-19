package com.magicbytes.githubtestsample.network

import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {
    @GET("posts")
    fun allPosts(): Call<List<Post>>

    @GET("users")
    fun allUsers(): Call<List<User>>

    @GET("comments")
    fun allComments(): Call<List<Comment>>
}