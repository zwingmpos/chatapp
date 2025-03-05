package com.zwing.chat.network.beans.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DummyModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    @Keep
    data class Data(
        @SerializedName("id")
        val cartId: Int,
        @SerializedName("qty")
        var qty: Int
    )
}