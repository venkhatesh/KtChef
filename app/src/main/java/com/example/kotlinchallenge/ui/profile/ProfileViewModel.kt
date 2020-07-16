package com.example.kotlinchallenge.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val TAG : String = "ProfileViewModel"
    var profileNetworkListener : ProfileNetworkListener ?= null
    var liveResult = MutableLiveData<ProfileResponse>()
    var loading = MutableLiveData<Boolean>()
    fun callProfileApi(userName:String)  {
        profileNetworkListener?.onStarted()
        loading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var result = profileRepository.fetchProfile(userName)
                //result?.let { profileNetworkListener?.onSuccess(it) }
                result?.let {
                    Log.d(TAG, "callProfileApi: Inside On Success ${it.user_details.name}")
                    loading.postValue(false)
                    liveResult.postValue(it)
                    profileNetworkListener?.onSuccess(it)
                    //profileRepository.saveUser(it)
                }?: kotlin.run {
                    Log.d(TAG, "callProfileApi: Inside On Failure")
                    loading.postValue(false)
                    profileNetworkListener?.onFailure("Something Went Wrong!")
                }
            }
        }
    }
}
