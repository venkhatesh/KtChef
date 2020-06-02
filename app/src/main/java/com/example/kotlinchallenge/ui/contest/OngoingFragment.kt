package com.example.kotlinchallenge.ui.contest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ViewUtils
import androidx.lifecycle.ViewModelProviders

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.toast

class OngoingFragment : Fragment(),NetworkListener {
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
        // TODO: Use the ViewModel
    }

    override fun onStarted() {
        activity?.toast("Fetching Data")
    }

    override fun onSuccess() {
        activity?.toast("Successfull Network Call")
    }

    override fun onFailure(message: String) {
        activity?.toast(message)
    }
}
