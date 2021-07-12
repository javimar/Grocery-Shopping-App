package eu.javimar.groceryshopping.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.javimar.domain.Grocery
import eu.javimar.groceryshopping.databinding.GroceryListItemBinding

class GroceryAdapter(private val groceryClickListener: GroceryClickListener) :
    ListAdapter<Grocery, GroceryAdapter.GroceryViewHolder>(GroceryDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder
    {
        return GroceryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int)
    {
        holder.bind(getItem(position), groceryClickListener)
    }

    class GroceryViewHolder private constructor(private val binding: GroceryListItemBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(grocery: Grocery, movieClickListener: GroceryClickListener)
        {
            binding.grocery = grocery
            binding.clickListener = movieClickListener

            binding.executePendingBindings()
        }

        companion object
        {
            fun from(parent: ViewGroup): GroceryViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GroceryListItemBinding.inflate(layoutInflater, parent, false)
                return GroceryViewHolder(binding)
            }
        }
    }


    class GroceryDiffCallback : DiffUtil.ItemCallback<Grocery>()
    {
        override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean
        {
            return oldItem == newItem
        }
    }

    // Custom listener that handles clicks on RecyclerView items
    class GroceryClickListener(val clickListener: (id: Int) -> Unit)
    {
        fun onClick(item: Grocery) = clickListener(item.id)
    }
}