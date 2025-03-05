package com.zwing.chat.network.repository

import com.zwing.chat.network.Constants
import com.zwing.chat.network.api.ApiService
import com.zwing.chat.network.base.BaseDataSource
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(val apiService: ApiService) : BaseDataSource() {
    fun getCartData() = performOperation(Constants.USER_DATA) {
        getResult(Constants.USER_DATA) {
            apiService.userData(
                hashMapOf(
                    "type" to "1"
                )
            )
        }
    }
}