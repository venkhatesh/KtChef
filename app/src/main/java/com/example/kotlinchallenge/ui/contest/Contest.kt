package com.example.kotlinchallenge.ui.contest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.example.kotlinchallenge.R
import com.google.android.material.tabs.TabLayout

class Contest : Fragment() {

    companion object {
        fun newInstance() = Contest()
    }
    val TAG = "Contest"
    private lateinit var viewModel: ContestViewModel
    private lateinit var pagerAdapter: ContestPageAdapter
    private lateinit var recyclerTabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//                val toolbar = findViewById(R.id.toolbar) as Toolbar
//
//            setSupportActionBar(toolbar)
//            supportActionBar?.setTitle("Code Chef")
        return inflater.inflate(R.layout.contest_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContestViewModel::class.java)
        Log.d(TAG, "onActivityCreated: Quotes Size ${viewModel.getQuotes()?.size}")
        val viewPager : ViewPager = activity?.findViewById(R.id.view_pager)!!
        pagerAdapter = ContestPageAdapter(requireActivity().supportFragmentManager)
        viewPager.adapter = pagerAdapter
        recyclerTabLayout = requireActivity().findViewById(R.id.tab_layout)
        recyclerTabLayout.setupWithViewPager(viewPager)
    }

}


