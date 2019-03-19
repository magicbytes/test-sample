package com.magicbytes.githubtestsample.ui.posts.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post
import com.magicbytes.githubtestsample.ui.details.PostDetailActivity
import com.squareup.picasso.Picasso

class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    // TODO improve this by moving click logic into adapter or any other hierarchy parent
    private var currentPost: Post? = null

    init {
        itemView.setOnClickListener { showDetailsPost() }
    }

    fun show(post: Post) {
        currentPost = post

        Picasso.get().load(Uri.parse("https://api.adorable.io/avatars/${post.userId}")).into(imageView)
        titleTextView.text = post.title
    }

    private fun showDetailsPost() {
        val post = currentPost ?: return

        PostDetailActivity.startWithTransition(titleTextView, imageView, post)
    }
}