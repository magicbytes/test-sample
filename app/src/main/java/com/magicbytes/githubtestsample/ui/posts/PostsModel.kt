package com.magicbytes.githubtestsample.ui.posts

import com.magicbytes.githubtestsample.network.Post

interface PostsModel {

    var eventsListener: Events?

    fun loadPosts()

    interface Events {
        fun onPostsLoaded(posts: List<Post>)

        fun onErrorLoadingPosts()
    }
}