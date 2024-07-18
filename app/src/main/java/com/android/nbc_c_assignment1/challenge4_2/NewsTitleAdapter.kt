package com.android.nbc_c_assignment1.challenge4_2

import android.service.quicksettings.Tile
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.nbc_login.databinding.LayoutNewsTitleItemBinding

class NewsTitleAdapter(val list: List<NewsItem>): ListAdapter<NewsItem, RecyclerView.ViewHolder>(DiffCallback()) {

    interface ItemClick {
        fun onClick(article: String)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutNewsTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is TitleViewHolder) holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class DiffCallback: DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class TitleViewHolder(private val binding: LayoutNewsTitleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItem) {
            binding.tvTitle.text = item.title
            binding.root.setOnClickListener {
                itemClick?.onClick(item.article)
            }
        }
    }



}