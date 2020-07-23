package com.example.kotlinchallenge.ui.contest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinchallenge.data.repositories.ContestRepository

/**
 * Created by Venkhatesh on 23-07-2020.
 */
class ContestViewModelFactory(private val repository: ContestRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContestViewModel(repository) as T
    }

}