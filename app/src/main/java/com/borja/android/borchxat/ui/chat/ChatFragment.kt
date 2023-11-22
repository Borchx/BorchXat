package com.borja.android.borchxat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.borja.android.borchxat.R
import com.borja.android.borchxat.databinding.FragmentChatBinding
import com.borja.android.borchxat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewmodel by viewModels<ChatViewModel>()

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.ivBack.setOnClickListener {
            viewmodel.logout { findNavController().navigate(R.id.action_back) }

        }
        setUpUI()
        binding.btnSendMsg.setOnClickListener {
            val msg = binding.edChat.text.toString()
            if (msg.isNotEmpty()) {
                viewmodel.sendMessage(msg)
            }
            binding.edChat.text.clear()
        }
        return binding.root
    }

    private fun setUpUI() {
        setUpUIMessage()
        subscribeToMessages()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        binding.tvTitle.text = viewmodel.name
    }

    private fun setUpUIMessage() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewmodel.messageList.collect {
                setUpToolbar()
                chatAdapter.updateList(it.toMutableList(), viewmodel.name)
                binding.rvMsg.scrollToPosition(chatAdapter.messageList.size - 1)
            }
        }
    }

}