package com.borja.android.borchxat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveUserName(nickname: String)
    fun getUserName(): Flow<String>
    suspend fun clear()
}