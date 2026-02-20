package com.cargram.app.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R
import com.cargram.app.model.DummyData
import com.cargram.app.model.Post
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SearchFragment : Fragment() {

    private lateinit var rvExplore: RecyclerView
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var chipGroup: ChipGroup
    private lateinit var exploreAdapter: ExploreAdapter
    private var allPosts = DummyData.explorePosts.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvExplore = view.findViewById(R.id.rv_explore)
        searchView = view.findViewById(R.id.search_view)
        chipGroup = view.findViewById(R.id.chip_group_categories)

        setupExploreGrid()
        setupSearch()
        setupChips()
    }

    private fun setupExploreGrid() {
        exploreAdapter = ExploreAdapter(allPosts) { post ->
            Toast.makeText(requireContext(), post.carModel.ifEmpty { "Car post" }, Toast.LENGTH_SHORT).show()
        }

        rvExplore.apply {
            layoutManager = GridLayoutManager(requireContext(), 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        // Every 7th item takes 2 columns (featured)
                        return if (position % 7 == 0) 2 else 1
                    }
                }
            }
            adapter = exploreAdapter
        }
    }

    private fun setupSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterPosts(query ?: "")
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterPosts(newText ?: "")
                return true
            }
        })
    }

    private fun setupChips() {
        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            when {
                checkedIds.contains(R.id.chip_supercars) -> filterByCategory("Supercar")
                checkedIds.contains(R.id.chip_classic) -> filterByCategory("Classic")
                checkedIds.contains(R.id.chip_trucks) -> filterByCategory("Truck")
                checkedIds.contains(R.id.chip_electric) -> filterByCategory("Electric")
                checkedIds.contains(R.id.chip_moto) -> filterByCategory("Motorcycle")
                else -> {
                    exploreAdapter.updatePosts(allPosts)
                }
            }
        }
    }

    private fun filterPosts(query: String) {
        if (query.isEmpty()) {
            exploreAdapter.updatePosts(allPosts)
            return
        }
        val filtered = allPosts.filter { post ->
            post.caption.contains(query, ignoreCase = true) ||
            post.carModel.contains(query, ignoreCase = true) ||
            post.user.username.contains(query, ignoreCase = true) ||
            post.hashtags.any { it.contains(query, ignoreCase = true) }
        }
        exploreAdapter.updatePosts(filtered)
    }

    private fun filterByCategory(category: String) {
        val filtered = allPosts.filter { it.carCategory.contains(category, ignoreCase = true) }
        if (filtered.isEmpty()) {
            exploreAdapter.updatePosts(allPosts)
        } else {
            exploreAdapter.updatePosts(filtered)
        }
    }
}
