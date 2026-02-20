package com.cargram.app.reels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.Reel
import de.hdodenhof.circleimageview.CircleImageView

class ReelsAdapter(
    private val reels: List<Reel>
) : RecyclerView.Adapter<ReelsAdapter.ReelViewHolder>() {

    inner class ReelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBg: ImageView = view.findViewById(R.id.iv_reel_bg)
        val ivAvatar: CircleImageView = view.findViewById(R.id.iv_reel_avatar)
        val tvUsername: TextView = view.findViewById(R.id.tv_reel_username)
        val tvCaption: TextView = view.findViewById(R.id.tv_reel_caption)
        val tvCarTag: TextView = view.findViewById(R.id.tv_reel_car_tag)
        val ivLike: ImageView = view.findViewById(R.id.iv_reel_like)
        val tvLikes: TextView = view.findViewById(R.id.tv_reel_likes)
        val ivComment: ImageView = view.findViewById(R.id.iv_reel_comment)
        val tvComments: TextView = view.findViewById(R.id.tv_reel_comments)
        val ivShare: ImageView = view.findViewById(R.id.iv_reel_share)
        val btnFollow: TextView = view.findViewById(R.id.btn_reel_follow)
        var isLiked = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reel, parent, false)
        // Make full screen height
        view.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            parent.height
        )
        return ReelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        val reel = reels[position]

        holder.tvUsername.text = reel.user.username
        holder.tvCaption.text = reel.caption
        holder.tvCarTag.text = reel.carTag
        holder.tvLikes.text = formatCount(reel.likesCount)
        holder.tvComments.text = formatCount(reel.commentsCount)

        holder.isLiked = reel.isLiked
        updateLikeIcon(holder)

        holder.ivLike.setOnClickListener {
            holder.isLiked = !holder.isLiked
            updateLikeIcon(holder)
            val msg = if (holder.isLiked) "❤️ Liked!" else "Unliked"
            Toast.makeText(holder.itemView.context, msg, Toast.LENGTH_SHORT).show()
        }

        holder.ivComment.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Comments for ${reel.user.username}'s reel", Toast.LENGTH_SHORT).show()
        }

        holder.ivShare.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Share ${reel.carTag}", Toast.LENGTH_SHORT).show()
        }

        holder.btnFollow.setOnClickListener {
            holder.btnFollow.text = "Following"
            Toast.makeText(holder.itemView.context, "Following ${reel.user.username}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLikeIcon(holder: ReelViewHolder) {
        if (holder.isLiked) {
            holder.ivLike.setImageResource(R.drawable.ic_heart_filled)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_heart)
            holder.ivLike.colorFilter = android.graphics.PorterDuffColorFilter(
                android.graphics.Color.WHITE,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000f)
            count >= 1_000 -> String.format("%.1fK", count / 1_000f)
            else -> count.toString()
        }
    }

    override fun getItemCount() = reels.size
}
