package com.cargram.app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.DummyData
import com.cargram.app.model.Post

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfile(view)
    }

    private fun setupProfile(view: View) {
        val user = DummyData.currentUser

        // Header
        view.findViewById<TextView>(R.id.tv_profile_username).text = user.username
        view.findViewById<TextView>(R.id.tv_full_name).text = user.fullName
        view.findViewById<TextView>(R.id.tv_bio).text = user.bio
        view.findViewById<TextView>(R.id.tv_car_title).text = user.carTitle
        view.findViewById<TextView>(R.id.tv_posts_count).text = formatCount(user.postsCount)
        view.findViewById<TextView>(R.id.tv_followers_count).text = formatCount(user.followersCount)
        view.findViewById<TextView>(R.id.tv_following_count).text = formatCount(user.followingCount)

        // Verified badge
        view.findViewById<View>(R.id.view_verified).visibility =
            if (user.isVerified) View.VISIBLE else View.GONE

        // Edit profile button
        view.findViewById<Button>(R.id.btn_edit_profile).setOnClickListener {
            Toast.makeText(requireContext(), "Edit Profile coming soon!", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btn_share_profile).setOnClickListener {
            Toast.makeText(requireContext(), "Sharing profile: @${user.username}", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageView>(R.id.iv_settings).setOnClickListener {
            Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageView>(R.id.iv_add_content).setOnClickListener {
            Toast.makeText(requireContext(), "Create new content", Toast.LENGTH_SHORT).show()
        }

        // Highlights
        val rvHighlights = view.findViewById<RecyclerView>(R.id.rv_highlights)
        rvHighlights.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = HighlightAdapter(DummyData.highlights) { name ->
                Toast.makeText(requireContext(), "Highlight: $name", Toast.LENGTH_SHORT).show()
            }
        }

        // Posts grid
        val rvGrid = view.findViewById<RecyclerView>(R.id.rv_posts_grid)
        rvGrid.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = GridPostAdapter(DummyData.posts) { post ->
                Toast.makeText(requireContext(), post.carModel.ifEmpty { "Car post" }, Toast.LENGTH_SHORT).show()
            }
            isNestedScrollingEnabled = false
        }
    }

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000f)
            count >= 1_000 -> String.format("%.1fK", count / 1_000f)
            else -> count.toString()
        }
    }
}
