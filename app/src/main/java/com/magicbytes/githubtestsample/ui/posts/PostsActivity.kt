package com.magicbytes.githubtestsample.ui.posts

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post
import com.magicbytes.githubtestsample.ui.posts.adapter.PostsAdapter

class PostsActivity : AppCompatActivity(), PostsView {

    private var recyclerView: RecyclerView? = null
    private var refreshLayout: SwipeRefreshLayout? = null

    private lateinit var presenter: PostsPresenter

    override var isLoadingVisible: Boolean = false
        set(value) {
            field = value

            refreshLayout?.isRefreshing = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        refreshLayout = findViewById(R.id.refreshLayout)
        refreshLayout?.setColorSchemeResources(R.color.pink, R.color.indigo, R.color.lime)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        // An empty adapter is necessary for enabling the refresh layout
        recyclerView?.adapter = PostsAdapter(emptyList())

        refreshLayout?.setOnRefreshListener { presenter.showPosts() }

        presenter = PostsPresenter(this, PostsModelGithub())
        presenter.showPosts()
    }

    override fun showPosts(posts: List<Post>) {
        recyclerView?.adapter = PostsAdapter(posts)
    }

    override fun showNoData() {
        val view = recyclerView ?: return
        val snackbar = Snackbar.make(view, "There was an error, please try again", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry") { presenter.showPosts() }
        snackbar.show()
    }
}
