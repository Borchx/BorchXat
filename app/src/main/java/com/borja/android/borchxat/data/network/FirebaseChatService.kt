package com.borja.android.borchxat.data.network

import com.borja.android.borchxat.data.network.dto.MessageDto
import com.borja.android.borchxat.data.network.response.MessageResponse
import com.borja.android.borchxat.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference:DatabaseReference){

    companion object{
        private const val PATH = "messages"
    }

    fun sendMsgToFirebase(messageDto: MessageDto){
        val newMsg = reference.child(PATH).push()
        newMsg.setValue(messageDto)
    }
    fun getMessage(): Flow<List<MessageModel>> {
        return reference.child(PATH).snapshots.map {dataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(MessageResponse::class.java)?.toDomain()
            }
        }
    }
}