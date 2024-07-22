package com.android.nbc_c_assignment1.challenge4_1.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.nbc_c_assignment1.challenge4_1.data.database.DataSource
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_c_assignment1.challenge4_1.data.repository.ContactListRepositoryImpl
import com.android.nbc_c_assignment1.challenge4_1.presentation.model.ContactListDataModel
import com.android.nbc_c_assignment1.challenge4_1.presentation.repository.ContactListRepository

class ContactListViewModel(private val contactListRepository: ContactListRepository) : ViewModel() {

    private val _contactListData = MutableLiveData<List<ContactListDataModel>>()
    val contactListData: LiveData<List<ContactListDataModel>> get() = _contactListData

    fun initContactListData() {
        _contactListData.value = contactListRepository.getContactList().sortModel()
    }

    fun setFavorite(position: Int) {
        val isFavorite = contactListData.value?.get(position)?.isFavorite
        changeFavorite(position, isFavorite ?: false)
        println(contactListData.value)
    }

    private fun changeFavorite(position: Int, isFavorite: Boolean) {
        _contactListData.value = contactListData.value?.mapIndexed { index, item ->
            if(position == index) {
                ContactListDataModel(
                    item.img,
                    item.name,
                    item.phoneNumber,
                    !isFavorite
                )
            } else {
                ContactListDataModel(
                    item.img,
                    item.name,
                    item.phoneNumber,
                    item.isFavorite
                )
            }
        }?.sortModel()
        println("값이 정렬된 후: ${contactListData.value}")
    }

    private fun List<ContactListDataModel>.sortModel(): List<ContactListDataModel> {
        return this.sortedWith(
            compareBy(
                { !it.isFavorite },
                { it.name },
            )
        )
    }
    fun getList() = contactListData.value
}

class ContactListViewmodelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ContactListViewModel(
            ContactListRepositoryImpl(DataSource)
        ) as T
    }
}