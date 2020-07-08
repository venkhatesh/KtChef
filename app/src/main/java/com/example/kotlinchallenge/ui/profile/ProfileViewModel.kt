package com.example.kotlinchallenge.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.repositories.ContestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun callProfileApi(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ContestRepository().fetchProfile()
            }
        }
    }
}
