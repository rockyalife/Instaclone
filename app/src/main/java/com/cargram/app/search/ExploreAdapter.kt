package com.cargram.app.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.Post

class ExploreAdapter(
    private var posts: List<Post>,
    private val onClick: (Post) -> Unit
) : RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    inner class ExploreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)
        val tvCarTag: TextView = view.findViewById(R.id.tv_car_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_explore_photo, parent, false)
        // Make square
        view.post {
            view.layoutParams.height = view.width
            view.requestLayout()
        }
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val post = posts[position]
        // Use placeholder; in production use Glide with post.imageUrl
        holder.ivPhoto.setImageResource(R.drawable.placeholder_car)
        holder.tvCarTag.text = post.hashtags.firstOrNull() ?: "#cargram"
        holder.itemView.setOnClickListener { onClick(post) }
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}
