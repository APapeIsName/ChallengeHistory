package com.android.nbc_c_assignment1.challenge4_1.data.database

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_login.R

private fun loadContactData(contentResolver: ContentResolver): List<ContactListDataEntity> {
    val cursor = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
    )
    val list: MutableList<ContactListDataEntity> = mutableListOf()
    if(cursor != null) {
        while (cursor.moveToNext()) {
            list.add(ContactListDataEntity(R.drawable.profile_basic, cursor.getString(0), cursor.getString(1), false))
        }
    }
    return list
}

// 정보 가져옴
fun contactList(context: Context, contentResolver: ContentResolver): List<ContactListDataEntity> {
    return if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
        println("성공")
//        loadContactData(contentResolver)
        listOf(
            ContactListDataEntity(R.drawable.profile_basic, "홍길동", "010-0000-0000", false),
            ContactListDataEntity(R.drawable.profile_basic, "김길동", "010-1234-0000", false),
            ContactListDataEntity(R.drawable.profile_basic, "박길동", "010-0567-0000", true),
            ContactListDataEntity(R.drawable.profile_basic, "박석두", "010-8790-0000", false),
        )
    } else {
        println("실패")
        listOf(
            ContactListDataEntity(R.drawable.profile_basic, "홍길동", "010-0000-0000", false),
            ContactListDataEntity(R.drawable.profile_basic, "김길동", "010-1234-0000", false),
            ContactListDataEntity(R.drawable.profile_basic, "박길동", "010-0567-0000", true),
            ContactListDataEntity(R.drawable.profile_basic, "박석두", "010-8790-0000", false),
        )
    }
}