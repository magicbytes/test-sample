package com.magicbytes.githubtestsample.ui.posts

import com.magicbytes.githubtestsample.network.Post

class PostsPresenter(
        private val view: PostsView,
        private val model: PostsModel
) : PostsModel.Events {

    init {
        model.eventsListener = this
    }

    fun showPosts() {
        view.isLoadingVisible = true
        model.loadPosts()
    }

    override fun onPostsLoaded(posts: List<Post>) {
        view.isLoadingVisible = false
        view.showPosts(posts)
    }

    override fun onErrorLoadingPosts() {
        view.isLoadingVisible = false
        view.showNoData()
    }
}