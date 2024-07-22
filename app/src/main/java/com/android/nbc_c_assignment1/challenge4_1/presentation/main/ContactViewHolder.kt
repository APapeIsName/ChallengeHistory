package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel

abstract class ContactViewHolder(binding: ViewBinding): ContactViewBinding(binding) {
    abstract fun bind(
        list: List<ContactListDataModel>,
        position: Int,
             itemLongClick: ContactListAdapter.ContactItemLongClick?,
        favoriteItemClick: ContactListAdapter.FavoriteItemClick?,
    )
}

abstract class ContactViewBinding(viewBinding: ViewBinding): RecyclerView.ViewHolder(viewBinding.root), ViewBinding by viewBinding