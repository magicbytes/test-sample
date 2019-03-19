package com.magicbytes.githubtestsample.ui.posts

import com.magicbytes.githubtestsample.network.NetworkManager
import com.magicbytes.githubtestsample.network.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsModelGithub : PostsModel {

    override var eventsListener: PostsModel.Events? = null

    override fun loadPosts() {
        NetworkManager.service().allPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                val z = 10 + 1
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val z = 10 + 1
            }
        })
    }

}