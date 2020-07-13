package com.example.kotlinchallenge.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val TAG : String = "ProfileViewModel"
    fun callProfileApi()  {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var result = profileRepository.fetchProfile()
                result?.user_details?.let {
                    Log.d(  TAG, "callProfileApi: " + it.name)
                    profileRepository.saveUser(it) }
            }
        }
    }
}
