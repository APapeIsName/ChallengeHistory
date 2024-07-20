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

class ContactListAdapter(private val viewModel: ContactListViewModel) : ListAdapter<ContactListDataModel, RecyclerView.ViewHolder>(
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
        return if (viewModel.getList()?.get(position)?.isFavorite == true) ViewType.FAVORITE.ordinal else ViewType.NORMAL.ordinal
    }

    override fun getItemCount(): Int = viewModel.getList()?.size ?: 0

    override fun getItemId(position: Int): Long = position.toLong()

    fun interface ItemClick {
        fun onClick()
    }
    fun setOnItemLongClickListener(itemClick: ItemClick?) {}

    var itemLongClick: ItemClick? = null

}