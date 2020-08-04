package com.example.kotlinchallenge.ui.contest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.getDatabase
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.databinding.FragmentOngoingBinding
import com.example.kotlinchallenge.databinding.FragmentPastBinding
import com.example.kotlinchallenge.util.verifyAvailableNetwork
import kotlinx.android.synthetic.main.fragment_ongoing.*
import kotlinx.android.synthetic.main.fragment_past.*

/**
 * Created by Venkhatesh on 30-07-2020.
 */
class PastFragment : Fragment() {
    val TAG:String = "OngoingFragment"

    private lateinit var viewModel: ContestViewModel
    private lateinit var adapter: ContestRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: FragmentPastBinding

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_past,container,false)
        val db = activity?.let { getDatabase(it) }
        val repository  = db?.let { ContestRepository(it) }
        val modelFactory = repository?.let { ContestViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this,modelFactory).get(ContestViewModel::class.java)
        binding.pastvar = viewModel
        binding.lifecycleOwner = this
        binding.shimmerLayout.startShimmer()
        if(activity?.verifyAvailableNetwork(activity as AppCompatActivity) == false){
            binding.listError.visibility = View.VISIBLE
            binding.pastRecycler.visibility = View.GONE
            binding.shimmerLayout.visibility = View.GONE

        }else{
            viewModel.callPastApi()
            binding.listError.visibility = View.GONE
            binding.pastRecycler.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.VISIBLE
        }
        linearLayoutManager = LinearLayoutManager(activity)
        Log.d(TAG,viewModel.liveResult.value?.size.toString())
        activity?.let {
            viewModel.liveResult.observe(it, Observer {
                Log.d(TAG,"Observable " + it.size)
                it.drop(1)
                past_recycler.let {
                    it.layoutManager = linearLayoutManager
                }
                //past_recycler.layoutManager = linearLayoutManager
                adapter = ContestRecyclerAdapter(it,"past")
                past_recycler.adapter = adapter
                val divider = DividerItemDecoration(past_recycler.getContext(), DividerItemDecoration.VERTICAL)
                divider.setDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.layout.custom_divider) }!!)
                past_recycler.addItemDecoration(divider)
            })
        }

        activity?.let {
            viewModel.loading.observe(it, Observer {
                if (it==true){
                    binding.shimmerLayout.visibility - View.VISIBLE
                    //shimmer_layout.visibility = View.VISIBLE
                    binding.pastRecycler.visibility = View.GONE
                    binding.shimmerLayout.startShimmer()
                }else{
                    binding.shimmerLayout.visibility = View.GONE
                    binding.pastRecycler.visibility = View.VISIBLE
                    binding.shimmerLayout.stopShimmer()
                }
            })
        }

        binding.swipeToRefreshPast.setOnRefreshListener {
            binding.swipeToRefreshPast.isRefreshing = false
            viewModel.callPastApi()
        }
        return binding.root
    }
}