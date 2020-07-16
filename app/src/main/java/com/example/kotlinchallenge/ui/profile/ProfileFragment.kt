package com.example.kotlinchallenge.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import kotlinx.android.synthetic.main.profile_fragment.*

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
        //val profileResponse = viewModel.callProfileApi()

        //fetch username from sharedpref
        var pref_user_name = activity?.getSharedPreferences("user_name",0)
        var userName = pref_user_name?.getString("user_name",null)
        Log.d(TAG, "onActivityCreated: Username : $userName")

        //Check If username is added to the local preference
        userName?.let {
            no_user_found_layout.visibility = View.GONE
            profile_layout.visibility = View.VISIBLE
            viewModel.callProfileApi(it)
        }?: kotlin.run {
            no_user_found_layout.visibility = View.VISIBLE
            profile_layout.visibility = View.GONE
            no_user_found_layout.setOnClickListener(View.OnClickListener {
                Log.d(TAG, "onActivityCreated: Inside If condition")
                val bottomSheetFragment = ProfileBottomSheetFragment()
                bottomSheetFragment.show(parentFragmentManager,bottomSheetFragment.tag)
            })
        }


        activity?.let {
            viewModel.loading.observe(it,Observer {
                swipe_to_refresh_profile.isRefreshing = it == true
            })
        }

        activity?.let {
            viewModel.liveResult.observe(it, Observer {
                it.let {
                    Log.d(TAG, "onActivityCreated: Username : ${it.user_details.name}")
                    profile_name_tv.text = it.user_details.name?.capitalize()
                    //profile_institution_tv.text = it.user_details.country
                    profile_country_tv.text = it.user_details.country
                    profile_global_rank_tv.text = it.global_rank
                    profile_country_rank_tv.text = it.country_rank
                    profile_rating_tv.text = it.rating
                }
            })
        }
        
    }

}
