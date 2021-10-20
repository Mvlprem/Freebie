package com.mvlprem.freebie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvlprem.freebie.databinding.RecyclerListBinding
import com.mvlprem.freebie.model.Games

/**
 * Adapter for recyclerView to create views and bind data
 */
class RecyclerAdapter(private val clickListener: GameItemClickListener) :
    ListAdapter<Games, RecyclerAdapter.ViewHolder>(DiffCall) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflater = RecyclerListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(games: Games, clickListener: GameItemClickListener) {
            binding.games = games
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /**
     *  calculates the difference between two lists
     */
    companion object DiffCall : DiffUtil.ItemCallback<Games>() {
        override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
            return oldItem == newItem
        }
    }
}

/**
 * ClickListener for RecyclerView Items
 */
class GameItemClickListener(val clickListener: (games: Games) -> Unit) {
    fun onClick(games: Games) = clickListener(games)
}
