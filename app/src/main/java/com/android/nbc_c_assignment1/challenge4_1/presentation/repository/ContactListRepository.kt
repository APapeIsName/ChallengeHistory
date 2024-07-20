package com.android.nbc_c_assignment1.challenge4_1.presentation.repository

import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel

interface ContactListRepository {
    fun getContactList(): List<ContactListDataModel>
}