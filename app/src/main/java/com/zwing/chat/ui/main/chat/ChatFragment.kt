package com.zwing.chat.ui.main.chat

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zwing.chat.core.BaseFragment
import com.zwing.chat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<ChatViewModel, FragmentChatBinding>() {
    lateinit var uriImage: Uri
    var message = ""
    private var mReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentFilter = IntentFilter("android.intent.action.MAIN")


        requireActivity().registerReceiver(mReceiver, intentFilter)
    }

    override fun getViewBinding() = FragmentChatBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(viewModel.doctorProfile.value).centerCrop().into(binding.profileImage)

        binding.apply {
            vm = viewModel
            frag = this@ChatFragment
            activity = requireActivity()
            tvName.text = "Dr. ${viewModel.doctorName.value}"
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            layoutManager.stackFromEnd = true
            layoutManager.isSmoothScrollbarEnabled = true
            rvChat.layoutManager = layoutManager

            etMsg.addTextChangedListener {
                if (it.toString().trim().isNullOrEmpty()) {
                    sendMsgButton.isEnabled = false
                    sendMsgButton.isClickable = false
                } else {
                    sendMsgButton.isEnabled = true
                    sendMsgButton.isClickable = true
                }
            }
        }

    }

    fun actionMenu(){

    }


    fun changeMessage(msg:String){

    }


    fun openAttachments(){

    }


    fun sendMessage(){

    }


}
