package com.cargram.app.reels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.DummyData

class ReelsFragment : Fragment() {

    private lateinit var rvReels: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvReels = view.findViewById(R.id.rv_reels)
        setupReels()
    }

    private fun setupReels() {
        val reelsAdapter = ReelsAdapter(DummyData.reels)

        rvReels.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = reelsAdapter
            // Snap to each reel (like TikTok / Instagram Reels)
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }
}
