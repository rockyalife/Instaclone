package com.cargram.app.home

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.Post
import com.cargram.app.model.Story

class FeedAdapter(
    private val items: List<FeedItem>,
    private val onLikeClick: (Post, Boolean) -> Unit,
    private val onCommentClick: (Post) -> Unit,
    private val onShareClick: (Post) -> Unit,
    private val onSaveClick: (Post, Boolean) -> Unit,
    private val onStoryClick: (Story) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_STORIES = 0
        const val TYPE_POST = 1
    }

    sealed class FeedItem {
        data class StoriesItem(val stories: List<Story>) : FeedItem()
        data class PostItem(val post: Post) : FeedItem()
    }

    // --- Stories ViewHolder ---
    inner class StoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvStories: RecyclerView = view.findViewById(R.id.rv_stories)
    }

    // --- Post ViewHolder ---
    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.iv_avatar)
        val tvUsername: TextView = view.findViewById(R.id.tv_username)
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val ivMore: ImageView = view.findViewById(R.id.iv_more)
        val ivPostImage: ImageView = view.findViewById(R.id.iv_post_image)
        val ivLike: ImageView = view.findViewById(R.id.iv_like)
        val ivComment: ImageView = view.findViewById(R.id.iv_comment)
        val ivShare: ImageView = view.findViewById(R.id.iv_share)
        val ivBookmark: ImageView = view.findViewById(R.id.iv_bookmark)
        val tvLikes: TextView = view.findViewById(R.id.tv_likes)
        val tvCaption: TextView = view.findViewById(R.id.tv_caption)
        val tvHashtags: TextView = view.findViewById(R.id.tv_hashtags)
        val tvViewComments: TextView = view.findViewById(R.id.tv_view_comments)
        val tvTimestamp: TextView = view.findViewById(R.id.tv_timestamp)
        val ivVerified: ImageView = view.findViewById(R.id.iv_verified)
        var isLiked: Boolean = false
        var isSaved: Boolean = false
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is FeedItem.StoriesItem -> TYPE_STORIES
            is FeedItem.PostItem -> TYPE_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_STORIES -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_stories_row, parent, false)
                StoriesViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post, parent, false)
                PostViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is FeedItem.StoriesItem -> {
                val storiesHolder = holder as StoriesViewHolder
                val storyAdapter = StoryAdapter(item.stories) { story ->
                    onStoryClick(story)
                }
                storiesHolder.rvStories.apply {
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        context, RecyclerView.HORIZONTAL, false
                    )
                    adapter = storyAdapter
                }
            }
            is FeedItem.PostItem -> {
                val postHolder = holder as PostViewHolder
                bindPost(postHolder, item.post)
            }
        }
    }

    private fun bindPost(holder: PostViewHolder, post: Post) {
        holder.tvUsername.text = post.user.username
        holder.tvTimestamp.text = post.timestamp

        // Location
        if (post.location.isNotEmpty()) {
            holder.tvLocation.visibility = View.VISIBLE
            holder.tvLocation.text = post.location
        } else {
            holder.tvLocation.visibility = View.GONE
        }

        // Verified
        holder.ivVerified.visibility = if (post.user.isVerified) View.VISIBLE else View.GONE

        // Likes
        holder.tvLikes.text = formatCount(post.likesCount) + " likes"

        // Caption: "username caption"
        val captionText = "${post.user.username} ${post.caption}"
        holder.tvCaption.text = captionText

        // Hashtags
        if (post.hashtags.isNotEmpty()) {
            holder.tvHashtags.visibility = View.VISIBLE
            holder.tvHashtags.text = post.hashtags.joinToString(" ")
        } else {
            holder.tvHashtags.visibility = View.GONE
        }

        // Comments
        holder.tvViewComments.text = "View all ${post.commentsCount} comments"

        // Like state
        holder.isLiked = post.isLiked
        updateLikeIcon(holder)

        // Save state
        holder.isSaved = post.isSaved
        updateSaveIcon(holder)

        // Like click
        holder.ivLike.setOnClickListener {
            holder.isLiked = !holder.isLiked
            animateLike(holder.ivLike)
            updateLikeIcon(holder)
            onLikeClick(post, holder.isLiked)
        }

        // Double tap to like
        holder.ivPostImage.setOnClickListener {
            if (!holder.isLiked) {
                holder.isLiked = true
                animateLike(holder.ivLike)
                updateLikeIcon(holder)
                onLikeClick(post, true)
            }
        }

        // Comment click
        holder.ivComment.setOnClickListener { onCommentClick(post) }

        // Share click
        holder.ivShare.setOnClickListener { onShareClick(post) }

        // Save click
        holder.ivBookmark.setOnClickListener {
            holder.isSaved = !holder.isSaved
            updateSaveIcon(holder)
            onSaveClick(post, holder.isSaved)
        }
    }

    private fun updateLikeIcon(holder: PostViewHolder) {
        if (holder.isLiked) {
            holder.ivLike.setImageResource(R.drawable.ic_heart_filled)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_heart)
        }
    }

    private fun updateSaveIcon(holder: PostViewHolder) {
        if (holder.isSaved) {
            holder.ivBookmark.setColorFilter(
                androidx.core.content.ContextCompat.getColor(holder.ivBookmark.context, R.color.blue_700)
            )
        } else {
            holder.ivBookmark.clearColorFilter()
        }
    }

    private fun animateLike(view: ImageView) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.4f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.4f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300
        scaleX.interpolator = OvershootInterpolator()
        scaleY.interpolator = OvershootInterpolator()
        scaleX.start()
        scaleY.start()
    }

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000f)
            count >= 1_000 -> String.format("%.1fK", count / 1_000f)
            else -> count.toString()
        }
    }

    override fun getItemCount() = items.size
}
