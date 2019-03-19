package com.magicbytes.githubtestsample.ui.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post

class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_post, parent, false)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.show(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}