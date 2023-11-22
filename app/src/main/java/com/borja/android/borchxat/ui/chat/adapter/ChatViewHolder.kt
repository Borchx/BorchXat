package com.borja.android.borchxat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.borja.android.borchxat.databinding.ItemChatMeBinding
import com.borja.android.borchxat.databinding.ItemChatOtherBinding
import com.borja.android.borchxat.domain.model.MessageModel

class ChatViewHolder(private val binding:ViewBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when(itemViewType){
            ChatAdapter.SENT_MESSAGE -> bindSendMessage(messageModel)
            ChatAdapter.RECEIVED_MESSAGE ->bindReceivedMessage(messageModel)
        }
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.tvDateOther.text = messageModel.date
        currentBinding.tvChatOther.text = messageModel.msg
        currentBinding.tvNameOther.text = messageModel.user.userName
        currentBinding.tvHourOther.text = messageModel.hour
    }

    private fun bindSendMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.tvDateMe.text = messageModel.date
        currentBinding.tvChatMe.text = messageModel.msg
        currentBinding.tvHour.text = messageModel.hour
    }
}