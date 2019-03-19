package com.magicbytes.githubtestsample.ui.posts

import com.magicbytes.githubtestsample.network.Post

interface PostsView {
    var isLoadingVisible: Boolean

    fun showPosts(posts: List<Post>)

    fun showNoData()
}