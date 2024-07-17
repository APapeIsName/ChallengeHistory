package com.android.nbc_c_assignment1.challenge4_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.nbc_c_assignment1.challenge3.nbc_login.ContractListDiffCallback
import com.android.nbc_login.databinding.LayoutContactListFavoriteBinding
import com.android.nbc_login.databinding.LayoutContactListNormalBinding

enum class ViewType {
    NORMAL, FAVORITE
}

class ContactListAdapter(private val viewModel: ContactListViewModel) : ListAdapter<ContactListData, RecyclerView.ViewHolder>(ContractListDiffCallback()) {
//    fun initList() {
//        val list = viewModel.getList()
//        if (list != null) {
//            val partition = list.partition { it.isFavorite }
//            favoriteList = partition.first
//            normalList = partition.second
//        }
//    }

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
        println("$viewHolder $viewType")
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NormalViewHolder -> holder.bind(viewModel, position, itemLongClick)
            is FavoriteViewHolder -> holder.bind(viewModel, position, itemLongClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        println("h $position ${viewModel.getList()?.get(position)?.isFavorite}")
        return if (viewModel.getList()?.get(position)?.isFavorite == true) ViewType.FAVORITE.ordinal else ViewType.NORMAL.ordinal
    }

    override fun getItemCount(): Int = viewModel.getList()!!.size

    override fun getItemId(position: Int): Long = position.toLong()

    interface ItemClick {
        fun onClick()
    }

    var itemLongClick: ItemClick? = null

}