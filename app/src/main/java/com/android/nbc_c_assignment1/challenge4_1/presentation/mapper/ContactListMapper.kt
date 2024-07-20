package com.android.nbc_c_assignment1.challenge4_1.presentation.mapper

import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel

fun List<ContactListDataEntity>.asModel(): List<ContactListDataModel> {
    return map {
        ContactListDataModel(
            it.img,
            it.name,
            it.phoneNumber,
            it.isFavorite
        )
    }
}