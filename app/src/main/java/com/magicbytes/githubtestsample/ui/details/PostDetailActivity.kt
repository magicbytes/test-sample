package com.magicbytes.githubtestsample.ui.details

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post
import com.squareup.picasso.Picasso


class PostDetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        imageView = findViewById(R.id.avatar)
        titleTextView = findViewById(R.id.title)

        ViewCompat.setTransitionName(imageView, VIEW_SHARED_IMAGE)
        ViewCompat.setTransitionName(titleTextView, VIEW_SHARED_TITLE)

        titleTextView.text = "Hello"

        val userId = intent.getIntExtra("userId", 1)
        Picasso.get().load(Uri.parse("https://api.adorable.io/avatars/$userId")).into(imageView)
    }

    companion object {
        const val VIEW_SHARED_TITLE = "shared:title"
        const val VIEW_SHARED_IMAGE = "shared:image"

        fun startWithTransition(title: TextView, avatar: ImageView, post: Post) {
            val intent = Intent(title.context, PostDetailActivity::class.java)
            intent.putExtra("userId", post.userId)


            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    title.context as Activity,
                    Pair<View, String>(title, VIEW_SHARED_TITLE),
                    Pair<View, String>(avatar, VIEW_SHARED_IMAGE)
            )

            ActivityCompat.startActivity(title.context, intent, activityOptions.toBundle())
        }
    }
}
