package com.cargram.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.DummyData
import com.cargram.app.model.Post
import com.cargram.app.model.Story

class HomeFragment : Fragment() {

    private lateinit var rvFeed: RecyclerView
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFeed = view.findViewById(R.id.rv_feed)
        setupFeed()
    }

    private fun setupFeed() {
        val feedItems = buildFeedItems()

        feedAdapter = FeedAdapter(
            items = feedItems,
            onLikeClick = ::onLikePost,
            onCommentClick = ::onCommentPost,
            onShareClick = ::onSharePost,
            onSaveClick = ::onSavePost,
            onStoryClick = ::onStoryClick
        )

        rvFeed.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = feedAdapter
            setHasFixedSize(false)
        }
    }

    private fun buildFeedItems(): List<FeedAdapter.FeedItem> {
        val items = mutableListOf<FeedAdapter.FeedItem>()
        // First item: stories row
        items.add(FeedAdapter.FeedItem.StoriesItem(DummyData.stories))
        // Then posts
        DummyData.posts.forEach { post ->
            items.add(FeedAdapter.FeedItem.PostItem(post))
        }
        return items
    }

    private fun onLikePost(post: Post, isLiked: Boolean) {
        val msg = if (isLiked) "Liked ${post.user.username}'s post!" else "Unliked"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onCommentPost(post: Post) {
        Toast.makeText(requireContext(), "Comments for ${post.carModel}", Toast.LENGTH_SHORT).show()
    }

    private fun onSharePost(post: Post) {
        Toast.makeText(requireContext(), "Share post about ${post.carModel}", Toast.LENGTH_SHORT).show()
    }

    private fun onSavePost(post: Post, isSaved: Boolean) {
        val msg = if (isSaved) "Post saved!" else "Post removed from saved"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onStoryClick(story: Story) {
        val msg = if (story.isOwn) "Add your story" else "${story.user.username}'s story"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}
