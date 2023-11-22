package com.borja.android.borchxat.domain

import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(userName: String){
        databaseService.saveUserName(userName)
    }
}