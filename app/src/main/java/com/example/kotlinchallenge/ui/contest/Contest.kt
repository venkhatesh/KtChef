package com.example.kotlinchallenge.ui.contest

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.getDatabase
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import com.example.kotlinchallenge.ui.profile.ProfileViewModelFactory
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
        val db = activity?.let { getDatabase(it) }
        val repository  = db?.let { ContestRepository(it) }
        val modelFactory = repository?.let { ContestViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this,modelFactory).get(ContestViewModel::class.java)
        var pref_quotes = activity?.getSharedPreferences("quotes",0)
        var quotes = pref_quotes?.getInt("quotes",0)
        //Log.d(TAG, "onActivityCreated: RandomQuotes ${viewModel.getRandomQuotes().en}")

        //Check if Quotes is Fetched or not and flag it accordingly
        if (quotes==0){
            viewModel.getQuotes()
            val editor: SharedPreferences.Editor? = pref_quotes?.edit()
            editor?.putInt("quotes",1)
            editor?.commit()
        }
        //tablayout viewpager
        val viewPager : ViewPager = activity?.findViewById(R.id.view_pager)!!
        pagerAdapter = ContestPageAdapter(requireActivity().supportFragmentManager)
        viewPager.adapter = pagerAdapter
        recyclerTabLayout = requireActivity().findViewById(R.id.tab_layout)
        recyclerTabLayout.setupWithViewPager(viewPager)
    }
}


