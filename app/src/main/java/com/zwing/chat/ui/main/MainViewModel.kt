package com.zwing.chat.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zwing.chat.network.repository.ApiHelperImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiHelper: ApiHelperImpl): ViewModel()  {


}