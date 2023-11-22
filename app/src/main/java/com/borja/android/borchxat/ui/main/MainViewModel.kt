package com.borja.android.borchxat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borja.android.borchxat.domain.GetUserNameUseCase
import com.borja.android.borchxat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val saveUserName: SaveUserNameUseCase, private val getUserNameUseCase: GetUserNameUseCase): ViewModel() {

    init {
        verifyUserLogged()

    }

    private var _uiState = MutableStateFlow<MainViewStates>(MainViewStates.LOADING)
    val uiState:StateFlow<MainViewStates> = _uiState

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val name = async { getUserNameUseCase()}.await()
            if(name.isNotEmpty()){
                _uiState.value = MainViewStates.REGISTERRED
            }else{
                _uiState.value = MainViewStates.UNREGISTERRED
            }
        }

    }

    fun saveNickName(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserName(nickname)
        }
    }
}

sealed class MainViewStates{
    object LOADING:MainViewStates()
    object UNREGISTERRED:MainViewStates()
    object REGISTERRED:MainViewStates()
}