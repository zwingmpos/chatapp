package com.zwing.chat.utils

import androidx.lifecycle.MutableLiveData

class Utility {

    companion object{
        val fromScreen: MutableLiveData<String> = MutableLiveData()
        var selectedPosition = 0
    }
}