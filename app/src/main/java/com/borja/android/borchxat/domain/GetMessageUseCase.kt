package com.borja.android.borchxat.domain

import com.borja.android.borchxat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService){

    operator fun invoke() = firebaseChatService.getMessage()

}