package com.android.nbc_c_assignment1.challenge4_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactListViewModel : ViewModel() {
    private val mList = mutableListOf<ContactListData>()

    private val _contactListData = MutableLiveData<List<ContactListData>>()
    val contactListData: LiveData<List<ContactListData>> get() = _contactListData

    fun submitList(list: List<ContactListData>) {
        mList.addAll(list)
        sortList()
        changeState()
    }

    fun setFavorite(position: Int) {
        if(contactListData.value?.get(position)?.isFavorite == false) addFavorite(position)
        else removeFavorite(position)
        sortList()
        changeState()
    }

    private fun addFavorite(position: Int) {
        mList[position].isFavorite = true
    }

    private fun removeFavorite(position: Int) {
        mList[position].isFavorite = false
    }

    private fun changeState() {
        _contactListData.value = mList
    }

    private fun sortList() {
        mList.sortWith(
            compareBy(
                { !it.isFavorite },
                { it.name },
            )
        )
    }

    fun getList() = contactListData.value
}