package com.example.kotlinchallenge.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.data.repositories.ProfileRepository

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private val TAG : String = "ProfileFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val db = activity?.let { AppDatabase(it) }
        val repository  = db?.let { ProfileRepository(it) }
        val modelFactory = repository?.let { ProfileViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this,modelFactory).get(ProfileViewModel::class.java)
        val profileResponse = viewModel.callProfileApi()
    }

}
