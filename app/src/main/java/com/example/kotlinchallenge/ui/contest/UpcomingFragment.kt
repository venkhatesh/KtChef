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
    lateinit var binding: FragmentUpcomingBinding

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ContestViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upcoming,container,false)
        binding.upcoming = viewModel
        binding.lifecycleOwner = this
        binding.shimmerLayoutUpcoming.startShimmer()
        viewModel.callUpcomingApi()
        linearLayoutManager = LinearLayoutManager(activity)
        activity?.let {
            viewModel.liveResult.observe(it, Observer {
                upcoming_recycler.layoutManager = linearLayoutManager
                adapter = ContestRecyclerAdapter(it,"upcoming")
                upcoming_recycler.adapter = adapter
                val divider = DividerItemDecoration(upcoming_recycler.getContext(), DividerItemDecoration.VERTICAL)
                divider.setDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.layout.custom_divider) }!!)
                upcoming_recycler.addItemDecoration(divider)
            })
        }

        activity?.let {
            viewModel.loading.observe(it, Observer {
                if (it==true){
                    binding.shimmerLayoutUpcoming.visibility - View.VISIBLE
                    binding.upcomingRecycler.visibility = View.GONE
                    binding.shimmerLayoutUpcoming.startShimmer()
                }else{
                    binding.shimmerLayoutUpcoming.visibility = View.GONE
                    binding.upcomingRecycler.visibility = View.VISIBLE
                    binding.shimmerLayoutUpcoming.stopShimmer()
                }
            })
        }

        binding.swipeToRefreshUpcoming.setOnRefreshListener {
            binding.swipeToRefreshUpcoming.isRefreshing = false
            viewModel.callOngoingApi()
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
