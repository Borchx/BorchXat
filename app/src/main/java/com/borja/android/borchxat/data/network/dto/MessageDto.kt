package com.borja.android.borchxat.data.network.dto

data class MessageDto (
    val msg:String,
    val hour:String,
    val date:String,
    val user:UserDto
    )

data class UserDto(
    val userName:String,
    val admin:Boolean
)
