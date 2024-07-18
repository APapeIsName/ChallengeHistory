package com.android.nbc_c_assignment1.challenge4_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel: ViewModel() {
    private val tempList: MutableList<NewsItem> = mutableListOf()

    private val _newsLiveData = MutableLiveData<List<NewsItem>>()
    val newsLiveData get() = _newsLiveData



}