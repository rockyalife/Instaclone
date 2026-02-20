package com.cargram.app.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.Story

class StoryAdapter(
    private val stories: List<Story>,
    private val onStoryClick: (Story) -> Unit
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.iv_avatar)
        val tvUsername: TextView = view.findViewById(R.id.tv_username)
        val ivAdd: ImageView = view.findViewById(R.id.iv_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]

        if (story.isOwn) {
            holder.tvUsername.text = "Your story"
            holder.ivAdd.visibility = View.VISIBLE
            // Show lighter ring for own story
            setUnviewedRing(holder, alpha = 0.4f)
        } else {
            holder.tvUsername.text = story.user.username
            holder.ivAdd.visibility = View.GONE
            if (story.isViewed) {
                setViewedRing(holder)
            } else {
                setUnviewedRing(holder)
            }
        }

        holder.itemView.setOnClickListener { onStoryClick(story) }
    }

    private fun setUnviewedRing(holder: StoryViewHolder, alpha: Float = 1f) {
        val ring = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.parseColor("#1565C0"), Color.parseColor("#42A5F5"))
        )
        ring.shape = GradientDrawable.OVAL
        ring.setStroke(4, Color.parseColor("#1565C0"))
        ring.alpha = (alpha * 255).toInt()
    }

    private fun setViewedRing(holder: StoryViewHolder) {
        // Viewed: grey ring
    }

    override fun getItemCount() = stories.size
}
