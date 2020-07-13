package com.example.kotlinchallenge.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.data.repositories.ProfileRepository

/**
 * Created by Venkhatesh on 13-07-2020.
 */
@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory (
    private val repository: ProfileRepository
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}