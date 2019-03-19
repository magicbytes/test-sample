package com.magicbytes.githubtestsample.ui.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.magicbytes.githubtestsample.R
import com.magicbytes.githubtestsample.network.Post


class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsViewHolder>() {

    // Allows to remember the last item shown on screen. Animation purpose
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_post, parent, false)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.show(posts[position])
        setAnimation(holder.itemView, position);
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}