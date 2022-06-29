package com.example.practiceafterwat.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceafterwat.data.model.Show
import com.example.practiceafterwat.databinding.ViewShowItemBinding
import com.example.practiceafterwat.util.layoutInflater
import com.example.practiceafterwat.util.show

class ShowListItemViewHolder(
    private val binding: ViewShowItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ShowListItemViewHolder {
            val binding = ViewShowItemBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
            return ShowListItemViewHolder(binding)
        }
    }

    fun init(show: Show, onClicked: (String) -> Unit) {
        with(binding) {
            itemPosterTitle.text = show.name
            itemPosterPost.show(show.image.medium)
            root.setOnClickListener {
                onClicked(show.id)
            }
        }
    }
}