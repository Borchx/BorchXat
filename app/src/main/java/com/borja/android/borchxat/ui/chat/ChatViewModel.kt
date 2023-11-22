package com.borja.android.borchxat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borja.android.borchxat.domain.GetMessageUseCase
import com.borja.android.borchxat.domain.GetUserNameUseCase
import com.borja.android.borchxat.domain.LogoutUseCase
import com.borja.android.borchxat.domain.SendMessageUseCase
import com.borja.android.borchxat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase
    ) : ViewModel() {

    var name:String = ""

    init{
        getUserName()
        getMessage()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
           name = getUserNameUseCase()
        }
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    private fun getMessage(){
        viewModelScope.launch {
            getMessageUseCase().collect{
                Log.d("Borchx", "La info es : $it")
                _messageList.value = it
            }
        }
    }

    fun sendMessage(msg:String) {
        sendMessageUseCase(msg,name)
    }

    fun logout(onViewFinish:() -> Unit) {
        viewModelScope.launch {
            async { logoutUseCase()}.await()
            onViewFinish()
        }
    }
}