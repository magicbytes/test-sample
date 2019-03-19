package com.magicbytes.githubtestsample.ui.details

import com.magicbytes.githubtestsample.network.Comment
import com.magicbytes.githubtestsample.network.User

interface PostDetailsContract {
    interface View {
        var isLoadingVisible: Boolean

        fun showError()

        fun showData(data: PostDetailViewIntent)
    }

    interface Model {
        var eventsListener: Events?

        fun loadUserInfo()

        fun loadComments()

        interface Events {
            fun onAllCommentsAvailable(comments: List<Comment>)

            fun onAllUsersAvailable(users: List<User>)

            fun onNetworkError()
        }
    }
}