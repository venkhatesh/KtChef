package com.example.kotlinchallenge.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.db.getDatabase
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val TAG : String = "ProfileViewModel"
    var liveResult = MutableLiveData<ProfileResponse>()
    var loading = MutableLiveData<Boolean>()
    var finalResult = MutableLiveData<List<QuotesResponse>>()
    fun callProfileApi(userName:String)  {
        loading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var result = profileRepository.fetchProfile(userName)
                //result?.let { profileNetworkListener?.onSuccess(it) }
                result?.let {
//                    Log.d(TAG, "callProfileApi: Inside On Success ${it.user_details.name}")
                    loading.postValue(false)
                    liveResult.postValue(it)
                    //profileRepository.saveUser(it)
                }?: kotlin.run {
                    Log.d(TAG, "callProfileApi: Inside On Failure")
                    loading.postValue(false)
                }
            }
        }
    }

}
