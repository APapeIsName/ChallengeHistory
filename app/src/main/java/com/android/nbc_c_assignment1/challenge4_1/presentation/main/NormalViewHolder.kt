package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.recyclerview.widget.RecyclerView
import com.android.nbc_login.databinding.LayoutContactListNormalBinding

class NormalViewHolder(private val binding: LayoutContactListNormalBinding) :
    ContactViewHolder(binding) {
    override fun bind(
        viewModel: ContactListViewModel,
        position: Int,
        itemLongClick: ContactListAdapter.ItemClick?
    ) {
        binding.apply {
            tvName.text = viewModel.getList()?.get(position)?.name ?: "이름"
            tvPhoneNum.text = viewModel.getList()?.get(position)?.phoneNumber ?: "010-8438-1217"
            ivFavorite.setOnClickListener {
                viewModel.setFavorite(position)
                println(viewModel.getList()?.get(position))
            }
            root.setOnLongClickListener {
                itemLongClick?.onClick()
                true
            }
        }
    }
}