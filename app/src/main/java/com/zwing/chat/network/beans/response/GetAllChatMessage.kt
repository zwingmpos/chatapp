package com.zwing.chat.network.beans.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GetAllChatMessage(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
) {
    @Keep
    data class Data(
        @SerializedName("message")
        val message: List<Message>
    ) {
        @Keep
        data class Message(
            @SerializedName("chat_user_id")
            val chatUserId: Int,
            @SerializedName("created_at")
            val createdAt: Long,
            @SerializedName("file")
            val `file`: String,
            @SerializedName("file_type")
            val fileType: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_read")
            val isRead: String,
            @SerializedName("message")
            val message: String,
            @SerializedName("message_type")
            val messageType: String,
            @SerializedName("room_id")
            val roomId: Int,
            @SerializedName("sender_type")
            val senderType: String,
            @SerializedName("user_id")
            val userId: Int
        )
    }
}