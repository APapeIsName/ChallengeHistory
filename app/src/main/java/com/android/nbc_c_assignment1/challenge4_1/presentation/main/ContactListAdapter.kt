package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel
import com.android.nbc_login.databinding.LayoutContactListFavoriteBinding
import com.android.nbc_login.databinding.LayoutContactListNormalBinding

enum class ViewType {
    NORMAL, FAVORITE
}

class ContactListAdapter : ListAdapter<ContactListDataModel, RecyclerView.ViewHolder>(
    ContractListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        when(viewType) {
            ViewType.NORMAL.ordinal -> {
                val binding = LayoutContactListNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = NormalViewHolder(binding)
            }
            else -> {
                val binding = LayoutContactListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = FavoriteViewHolder(binding)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NormalViewHolder -> {
                println("비교: $position")
                holder.bind(currentList, position, itemLongClick, favoriteItemClick, )
            }
            is FavoriteViewHolder -> {
                holder.bind(currentList, position, itemLongClick, favoriteItemClick, )
            }
        }
        println("이게 무슨 상황 $position $currentList")
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isFavorite) ViewType.FAVORITE.ordinal else ViewType.NORMAL.ordinal
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemId(position: Int): Long = position.toLong()

    fun interface ContactItemLongClick {
        fun onLongClick()
    }

    fun interface FavoriteItemClick {
        fun onClick(position: Int)
    }

    fun setOnItemLongClickListener(itemClick: ContactItemLongClick?) {
        if(itemClick != null) {
            itemLongClick = itemClick
        }
    }

    fun setOnFavoriteClickListener(itemClick: FavoriteItemClick?) {
        if (itemClick != null)
            favoriteItemClick = itemClick
    }

    private var itemLongClick: ContactItemLongClick? = null

    private var favoriteItemClick: FavoriteItemClick? = null

}