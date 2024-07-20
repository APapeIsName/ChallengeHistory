package com.android.nbc_c_assignment1.challenge4_1.data.database

import android.content.ContentResolver
import android.content.Context
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity

object DataSource {
    lateinit var context: Context
    lateinit var contentResolver: ContentResolver
    fun initDataSource(context: Context, contentResolver: ContentResolver) {
        this.context = context
        this.contentResolver = contentResolver
    }
    fun getContactList() : List<ContactListDataEntity> {
        return contactList(context, contentResolver)
    }
}