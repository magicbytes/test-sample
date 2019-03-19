package com.magicbytes.githubtestsample.ui.posts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post

class PostsActivity : AppCompatActivity(), PostsView {

    private lateinit var presenter: PostsPresenter

    override var isLoadingVisible: Boolean = false
        set(value) {
            field = value


        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        presenter = PostsPresenter(this, PostsModelGithub())
    }

    override fun showPosts(posts: List<Post>) {

    }

    override fun showNoData() {
    }
}
