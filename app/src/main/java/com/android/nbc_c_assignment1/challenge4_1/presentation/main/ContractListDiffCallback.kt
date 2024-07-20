package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel

class ContractListDiffCallback : DiffUtil.ItemCallback<ContactListDataModel>() {

    override fun areItemsTheSame(oldItem: ContactListDataModel, newItem: ContactListDataModel): Boolean {
        return oldItem.phoneNumber == newItem.phoneNumber
    }

    override fun areContentsTheSame(oldItem: ContactListDataModel, newItem: ContactListDataModel): Boolean {
         return oldItem == newItem
    }

}