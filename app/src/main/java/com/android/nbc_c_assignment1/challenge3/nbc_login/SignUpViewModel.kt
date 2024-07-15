package com.android.nbc_c_assignment1.challenge3.nbc_login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private val _user = MutableLiveData<UserClass>()
    val user: LiveData<UserClass> get() = _user

    fun changeUser(id: String, pwd: String, name: String, chicken: String) {
        _user.value = UserClass(id, pwd, name, chicken)
    }

    fun getUser() = user.value
}