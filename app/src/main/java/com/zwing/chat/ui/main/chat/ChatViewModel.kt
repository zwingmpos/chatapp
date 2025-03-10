package com.zwing.chat.ui.main.chat

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zwing.chat.network.repository.ApiHelperImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val apiHelper: ApiHelperImpl) : ViewModel() {

    val enableSendButton by lazy { MutableLiveData(false) }
    val bookId by lazy { MutableLiveData("") }
    val doctorProfile by lazy { MutableLiveData("") }
    val doctorName by lazy { MutableLiveData("") }
    val doctorId by lazy { MutableLiveData("") }
  /*  fun getConsultationList() = apiHelper.getConsultationList().cachedIn(viewModelScope)
    fun getAllChatMessage(bookId: String) = apiHelper.getAllChatMessage(bookId)
    fun sendTextMessage(messageText: String) = apiHelper.sendTextMessage(bookId.value!!, messageText)
    fun sendImageMessage(file: File, context: Context) = apiHelper.sendImageMessage(file,bookId.value!!, context)
    fun deleteConsultation() = apiHelper.deleteConsultation(bookId.value!!)
    fun rejectCall() = apiHelper.rejectCall()
    fun acceptCall(token: String?) = apiHelper.acceptCall(token)
    fun getUserNotification() = apiHelper.getUserNotification().cachedIn(viewModelScope)
    fun getUserProfile() = apiHelper.getUserProfile()*/
}