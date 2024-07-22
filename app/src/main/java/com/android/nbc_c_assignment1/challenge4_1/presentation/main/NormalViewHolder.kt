package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel
import com.android.nbc_login.databinding.LayoutContactListNormalBinding

class NormalViewHolder(private val binding: LayoutContactListNormalBinding) :
    ContactViewHolder(binding) {
    override fun bind(
        list: List<ContactListDataModel>,
        position: Int,
        itemLongClick: ContactListAdapter.ContactItemLongClick?,
        favoriteItemClick: ContactListAdapter.FavoriteItemClick?,
    ) {
        binding.apply {
            println("노말 $position")
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