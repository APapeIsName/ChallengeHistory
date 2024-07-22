package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ContactViewHolder(binding: ViewBinding): ContactViewBinding(binding) {
    abstract fun bind(viewModel: ContactListViewModel,
             position: Int,
             itemLongClick: ContactListAdapter.ItemClick?)
}

abstract class ContactViewBinding(viewBinding: ViewBinding): RecyclerView.ViewHolder(viewBinding.root), ViewBinding by viewBinding