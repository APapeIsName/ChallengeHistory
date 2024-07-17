package com.android.nbc_c_assignment1.challenge3.nbc_login

import androidx.recyclerview.widget.DiffUtil
import com.android.nbc_c_assignment1.challenge4_1.ContactListData

class ContractListDiffCallback : DiffUtil.ItemCallback<ContactListData>() {
    override fun areItemsTheSame(oldItem: ContactListData, newItem: ContactListData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactListData, newItem: ContactListData): Boolean {
         return oldItem == newItem
    }

}