package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.recyclerview.widget.RecyclerView
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel
import com.android.nbc_login.databinding.LayoutContactListFavoriteBinding

class FavoriteViewHolder(private val binding: LayoutContactListFavoriteBinding) :
    ContactViewHolder(binding){
    override fun bind(
        list: List<ContactListDataModel>,
        position: Int,
        itemLongClick: ContactListAdapter.ContactItemLongClick?,
        favoriteItemClick: ContactListAdapter.FavoriteItemClick?,
    ) {
        binding.apply {
            println("페이보릿 $position")
            tvName.text = list[position].name
            tvPhoneNum.text = list[position].phoneNumber
            ivFavorite.setOnClickListener {
                favoriteItemClick?.onClick(position)
            }
            root.setOnLongClickListener {
                itemLongClick?.onLongClick()
                true
            }
        }
    }
}