package com.example.practiceafterwat.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.practiceafterwat.data.model.Show

class ShowListAdapter(private val onClicked: (String) -> Unit) :
    ListAdapter<Show, ShowListItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListItemViewHolder {
        return ShowListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShowListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.init(item, onClicked)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }
}