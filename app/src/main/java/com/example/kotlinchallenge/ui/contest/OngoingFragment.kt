package com.example.kotlinchallenge.ui.contest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.DataResponse
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.toast

class OngoingFragment : Fragment(),NetworkListener {

    val TAG:String = "Ongoing Fragment"
    companion object {
        fun newInstance() = OngoingFragment()
    }
    private lateinit var viewModel: ContestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContestViewModel::class.java)
        viewModel.callOngoingApi()
        // TODO: Use the ViewModel
    }

    override fun onStarted() {
        activity?.toast("Fetching Data")
        Log.d(TAG,"Network Started")
    }

    override fun onSuccess(response: List<ArrayDataResponse>) {
        activity?.toast("Successfull Network Call")
        Log.d(TAG, response.get(1).Name)
    }

    override fun onFailure(message: String) {
        activity?.toast(message)
        Log.d(TAG,message)
    }
}
