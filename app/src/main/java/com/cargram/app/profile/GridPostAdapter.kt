package com.cargram.app.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.Post

class GridPostAdapter(
    private val posts: List<Post>,
    private val onClick: (Post) -> Unit
) : RecyclerView.Adapter<GridPostAdapter.GridViewHolder>() {

    inner class GridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.iv_grid_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_post, parent, false)
        // Make square cells
        val size = parent.width / 3
        view.layoutParams = RecyclerView.LayoutParams(size, size)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val post = posts[position]
        holder.ivImage.setImageResource(R.drawable.placeholder_car)
        holder.itemView.setOnClickListener { onClick(post) }
    }

    override fun getItemCount() = posts.size
}
