package com.example.kotlinchallenge.ui.contest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.databinding.FragmentOngoingBinding
import com.example.kotlinchallenge.databinding.FragmentUpcomingBinding
import com.example.kotlinchallenge.ui.NetworkListener
import kotlinx.android.synthetic.main.fragment_ongoing.*
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment(),NetworkListener {


    private lateinit var viewModel: ContestViewModel
    private lateinit var adapter: ContestRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ContestViewModel::class.java)
        val binding : FragmentUpcomingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_upcoming,container,false)
        binding.upcoming = viewModel
        binding.lifecycleOwner = this
        viewModel.callUpcomingApi()
        linearLayoutManager = LinearLayoutManager(activity)
        activity?.let {
            viewModel.liveResult.observe(it, Observer {
                upcoming_recycler.layoutManager = linearLayoutManager
                adapter = ContestRecyclerAdapter(it)
                upcoming_recycler.adapter = adapter
                val divider = DividerItemDecoration(upcoming_recycler.getContext(), DividerItemDecoration.VERTICAL)
                divider.setDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.layout.custom_divider) }!!)
                upcoming_recycler.addItemDecoration(divider)
            })
        }

        return binding.root
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(onGoingResponse: List<ArrayDataResponse>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }


}
