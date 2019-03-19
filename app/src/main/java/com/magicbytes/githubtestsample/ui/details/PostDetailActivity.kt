package com.magicbytes.githubtestsample.ui.details

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post
import com.squareup.picasso.Picasso


class PostDetailActivity : AppCompatActivity(), PostDetailsContract.View {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bodyTextView: TextView
    private lateinit var messageNumber: TextView
    private lateinit var authorName: TextView
    private lateinit var progressBar: View

    private lateinit var presenter: PostDetailsPresenter

    override var isLoadingVisible: Boolean = false
        set(value) {
            field = value

            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        imageView = findViewById(R.id.avatar)
        titleTextView = findViewById(R.id.title)
        bodyTextView = findViewById(R.id.body)
        progressBar = findViewById(R.id.progressBar)
        messageNumber = findViewById(R.id.messageNumber)
        authorName = findViewById(R.id.authorName)

        ViewCompat.setTransitionName(imageView, VIEW_SHARED_IMAGE)
        ViewCompat.setTransitionName(titleTextView, VIEW_SHARED_TITLE)

        presenter = PostDetailsPresenter(this, PostDetailsGithubModel())

        val postJson = intent.getStringExtra("post")
        if (postJson != null) {
            val post = Gson().fromJson(postJson, Post::class.java)
            presenter.showPostDetails(post)

            titleTextView.text = post.title
            bodyTextView.text = post.body
            Picasso.get().load(Uri.parse("https://api.adorable.io/avatars/${post.userId}")).into(imageView)
        }
    }

    override fun showError() {
        val snackbar = Snackbar.make(imageView, "There was an error", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry") {
            val postJson = intent.getStringExtra("post")
            val post = Gson().fromJson(postJson, Post::class.java)
            presenter.showPostDetails(post)
        }
        snackbar.show()
    }

    override fun showData(data: PostDetailViewIntent) {
        authorName.text = data.authorName
        messageNumber.text = data.commentsNumber
    }

    companion object {
        const val VIEW_SHARED_TITLE = "shared:title"
        const val VIEW_SHARED_IMAGE = "shared:image"

        fun startWithTransition(title: TextView, avatar: ImageView, post: Post) {
            val intent = Intent(title.context, PostDetailActivity::class.java)
            intent.putExtra("post", Gson().toJson(post))


            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    title.context as Activity,
                    Pair<View, String>(title, VIEW_SHARED_TITLE),
                    Pair<View, String>(avatar, VIEW_SHARED_IMAGE)
            )

            ActivityCompat.startActivity(title.context, intent, activityOptions.toBundle())
        }
    }
}
