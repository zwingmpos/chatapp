package com.zwing.chat.ui.main.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zwing.chat.databinding.ListLeftMessagesBinding
import com.zwing.chat.databinding.ListRightMessagesBinding
import com.zwing.chat.network.beans.response.GetAllChatMessage
import com.zwing.chat.utils.ViewTypes

class ChatAdapter(
    private val list: ArrayList<GetAllChatMessage.Data.Message>,
    val onClick: (Int, GetAllChatMessage.Data.Message) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewTypes.VIEW_TYPE_USER)
            UserMessageViewHolder(ListRightMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else
            DrMessageViewHolder(ListLeftMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = list[position]

        if (getItemViewType(position) == ViewTypes.VIEW_TYPE_USER) {
            (holder as UserMessageViewHolder)
            holder.binding.apply {
                when (result.messageType) {
                    "text" -> {
                        clUserMessage.visibility = View.VISIBLE
                        ivImageMessage.visibility = View.GONE
                        txtChat.visibility = View.VISIBLE
                        txtChat.text = (result.message ?: "file___Message").toString()
                    }
                    "image" -> {
                        clUserMessage.visibility = View.VISIBLE
                        txtChat.visibility = View.GONE
                        ivImageMessage.visibility = View.VISIBLE
                        Glide.with(ivImageMessage.context).load(result.file).into(ivImageMessage)
                        ivImageMessage.setOnClickListener { onClick(1,result) }
                    }
                    "pdf" -> {
                        clUserMessage.visibility = View.GONE
                        ivImageMessage.visibility = View.GONE
                       // ivDrPrescriptionImage.setOnClickListener { onClick(2,result) }
                    }
                }
                time.visibility = View.VISIBLE
                time.text = result.createdAt.toString()

            }
        } else if (getItemViewType(position) == ViewTypes.VIEW_TYPE_OTHER) {
            (holder as DrMessageViewHolder)
            holder.binding.apply {
                when (result.messageType) {
                    "message" -> {
                        clPrescription.visibility = View.GONE
                        clDrMessage.visibility = View.VISIBLE
                        ivImageMessage.visibility = View.GONE
                        txtChat.visibility = View.VISIBLE
                        tvConsultDetails.visibility = View.GONE
                        txtChat.text = (result.message ?: "file___Message").toString()
                    }
                    "file" -> {
                        clPrescription.visibility = View.GONE
                        clDrMessage.visibility = View.VISIBLE
                        ivImageMessage.visibility = View.VISIBLE
                        txtChat.visibility = View.GONE
                        tvConsultDetails.visibility = View.GONE
                        Glide.with(ivImageMessage.context).load(result.file).into(ivImageMessage)
                        ivImageMessage.setOnClickListener { onClick(1,result) }
                    }
                    "prescription" -> {
                        clPrescription.visibility = View.VISIBLE
                        clDrMessage.visibility = View.GONE
                        ivImageMessage.visibility = View.GONE
                        tvTimePre.text = result.createdAt.toString()
                        llPreView.setOnClickListener  { onClick(2,result) }
                      //  tvPrescriptionFileName.text = "My Prescription_${viewModel.doctorName.value!!}.pdf"
                    }
                    else -> {
                        clPrescription.visibility = View.GONE
                        ivImageMessage.visibility = View.GONE
                        clDrMessage.visibility = View.VISIBLE
                        tvConsultDetails.visibility = View.VISIBLE
                        tvConsultDetails.text = result.message
                        txtChat.visibility = View.GONE
                        time.visibility = View.GONE
                        tvTimePre.text = result.createdAt.toString()
                        //ivDrPrescriptionImage.setOnClickListener  { onClick(2,result) }
                    }
                }
                time.text = result.createdAt.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val result = list[position]
        return if (result.senderType == "user") {
            ViewTypes.VIEW_TYPE_USER
        } else {
            ViewTypes.VIEW_TYPE_OTHER
        }
    }

    fun addMessageItem(data: GetAllChatMessage.Data.Message) {
        list.add(data)
        notifyDataSetChanged()
    }

    class UserMessageViewHolder(val binding: ListRightMessagesBinding) : RecyclerView.ViewHolder(binding.root) {}

    class DrMessageViewHolder(val binding: ListLeftMessagesBinding) : RecyclerView.ViewHolder(binding.root) {}


    override fun getItemCount(): Int {
        return list.size
    }
}

