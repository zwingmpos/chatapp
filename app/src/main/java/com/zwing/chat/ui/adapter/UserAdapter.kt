package com.zwing.chat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zwing.chat.R
import com.zwing.chat.core.BaseViewHolder
import com.zwing.chat.databinding.ItemLayoutBinding
import com.zwing.chat.network.beans.response.DummyModel

class UserAdapter(
    val list: ArrayList<DummyModel.Data>,
    private val clicked: (ArrayList<DummyModel.Data>,Int) -> Unit
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(binding: ItemLayoutBinding) :
        BaseViewHolder<ItemLayoutBinding>(binding) {

        fun bind(position: Int) {
            binding.apply {
                clRoot.setOnClickListener {
                    clicked(list,position)
                }
            }
        }
    }
}