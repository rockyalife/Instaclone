package com.cargram.app.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cargram.app.R

class HighlightAdapter(
    private val highlights: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder>() {

    inner class HighlightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.iv_highlight_cover)
        val tvName: TextView = view.findViewById(R.id.tv_highlight_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_highlight, parent, false)
        return HighlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val name = highlights[position]
        holder.tvName.text = name
        holder.ivCover.setImageResource(R.drawable.placeholder_car)
        holder.itemView.setOnClickListener { onClick(name) }
    }

    override fun getItemCount() = highlights.size
}
