package com.borja.android.borchxat.data.network.response

import com.borja.android.borchxat.domain.model.MessageModel
import com.borja.android.borchxat.domain.model.UserModel

data class MessageResponse (
    val msg:String? = null,
    val hour:String? = null,
    val date:String? = null,
    val user:UserResponse ? = null
) {
    fun toDomain():MessageModel {
        return MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no date",
            date = date.orEmpty(),
            user = UserModel(userName = user?.userName ?: "Guess", admin = user?.admin ?: false)
        )
    }
}

data class UserResponse(
    val userName:String? = null,
    val admin:Boolean? = null
)