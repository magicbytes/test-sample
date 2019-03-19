package com.magicbytes.githubtestsample.ui.details

import com.magicbytes.githubtestsample.network.Comment
import com.magicbytes.githubtestsample.network.Post
import com.magicbytes.githubtestsample.network.User

class PostDetailsPresenter(
        private val view: PostDetailsContract.View,
        private val model: PostDetailsContract.Model
) : PostDetailsContract.Model.Events {

    private var post = Post(1, 1, "", "")
    private var allUsers: List<User> = emptyList()

    init {
        model.eventsListener = this
    }

    fun showPostDetails(post: Post) {
        this.post = post

        view.isLoadingVisible = true
        model.loadUserInfo()
    }

    override fun onAllCommentsAvailable(comments: List<Comment>) {
        view.isLoadingVisible = false

        val user = allUsers.firstOrNull { it.id == post.userId } ?: return
        val commentsCount = comments.count { it.postId == post.id }
        view.showData(PostDetailViewIntent(user.name, commentsCount.toString()))
    }

    override fun onAllUsersAvailable(users: List<User>) {
        allUsers = users
        model.loadComments()
    }

    override fun onNetworkError() {
        view.isLoadingVisible = false
        view.showError()
    }
}