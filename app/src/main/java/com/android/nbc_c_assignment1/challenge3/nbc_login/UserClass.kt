package com.android.nbc_c_assignment1.challenge3.nbc_login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserClass(val id: String, val pwd: String, val name: String, val chicken: String) : Parcelable {
    override fun toString(): String {
        return "아이디: ${id}, 비밀번호: ${pwd}, 이름: ${name}, 치킨은 $chicken "
    }
}