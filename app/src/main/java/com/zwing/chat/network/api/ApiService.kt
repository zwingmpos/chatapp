package com.zwing.chat.network.api

import com.zwing.chat.network.Constants.USER_DATA
import com.zwing.chat.network.beans.response.DummyModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(USER_DATA)
    suspend fun userData(@Body map : HashMap<String, String>): Response<DummyModel>
}
