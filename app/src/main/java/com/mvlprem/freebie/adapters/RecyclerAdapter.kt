package com.mvlprem.freebie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvlprem.freebie.databinding.RecyclerListBinding
import com.mvlprem.freebie.model.Games

/**
 * RecyclerView Adapter create views and bind data
 * @param clickListener RecyclerView Items clickListener Class
 */
class RecyclerAdapter(private val clickListener: GameItemClickListener) :
    ListAdapter<Games, RecyclerAdapter.ViewHolder>(DiffCall) {

    /**
     * Called when RecyclerView needs a new view of the given type to
     * represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflater = RecyclerListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(inflater)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    /**
     * ViewHolder for Freebie items. All work is done by data binding.
     */
    class ViewHolder(private val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(games: Games, clickListener: GameItemClickListener) {
            binding.games = games
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /**
     *  Checks the differences in old & new list
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
    /**
     * called when a game is clicked
     * @param games game that was clicked
     */
    fun onClick(games: Games) = clickListener(games)
}
