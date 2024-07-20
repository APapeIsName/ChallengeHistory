package com.android.nbc_c_assignment1.challenge4_1.data.repository

import com.android.nbc_c_assignment1.challenge4_1.data.database.DataSource
import com.android.nbc_c_assignment1.challenge4_1.presentation.mapper.asModel
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel
import com.android.nbc_c_assignment1.challenge4_1.presentation.repository.ContactListRepository

class ContactListRepositoryImpl(private val dataSource: DataSource): ContactListRepository {
    override fun getContactList(): List<ContactListDataModel> = dataSource.getContactList().asModel()

}