package com.example.kotlinchallenge.ui.contest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.databinding.FragmentOngoingBinding
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.toast
import kotlinx.android.synthetic.main.fragment_ongoing.*

class OngoingFragment : Fragment(),NetworkListener {

    val TAG:String = "OngoingFragment"

    private lateinit var viewModel: ContestViewModel
    private lateinit var adapter: ContestRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    @SuppressLint("ResourceType")
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"Ongoing Fragment")
        val binding : FragmentOngoingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_ongoing,container,false)
        viewModel = ViewModelProviders.of(this).get(ContestViewModel::class.java)
        viewModel.callOngoingApi()
        binding.ongoingvar = viewModel
        binding.lifecycleOwner = this
        linearLayoutManager = LinearLayoutManager(activity)
        Log.d(TAG,viewModel.liveResult.value?.size.toString())
        activity?.let {
            viewModel.liveResult.observe(it, Observer {
                Log.d(TAG,"Observable " + it.size)
                ongoing_recycler.layoutManager = linearLayoutManager
                adapter = ContestRecyclerAdapter(it)
                ongoing_recycler.adapter = adapter
                val divider = DividerItemDecoration(ongoing_recycler.getContext(), DividerItemDecoration.VERTICAL)
                divider.setDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.layout.custom_divider) }!!)
                ongoing_recycler.addItemDecoration(divider)

            })
        }
        return binding.root
    }


    override fun onStarted() {
        Log.d(TAG,"Network Started")
    }

    override fun onSuccess(response: List<ArrayDataResponse>) {
        activity?.toast("Successfull Network Call")
    }

    override fun onFailure(message: String) {
        activity?.toast(message)
        Log.d(TAG,"Inside Error" + message)
    }
}
