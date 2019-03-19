package com.magicbytes.githubtestsample.ui.details

import com.magicbytes.githubtestsample.network.Comment
import com.magicbytes.githubtestsample.network.NetworkManager
import com.magicbytes.githubtestsample.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsGithubModel : PostDetailsContract.Model {
    override var eventsListener: PostDetailsContract.Model.Events? = null

    override fun loadUserInfo() {
        NetworkManager.service().allUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                eventsListener?.onNetworkError()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val body = response.body()
                if (body != null) {
                    eventsListener?.onAllUsersAvailable(body)
                } else {
                    eventsListener?.onNetworkError()
                }
            }
        })
    }

    override fun loadComments() {
        NetworkManager.service().allComments().enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                eventsListener?.onNetworkError()
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val body = response.body()
                if (body != null) {
                    eventsListener?.onAllCommentsAvailable(body)
                } else {
                    eventsListener?.onNetworkError()
                }
            }
        })
    }
}