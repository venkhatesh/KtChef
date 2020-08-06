package com.example.kotlinchallenge.ui.profile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.db.getDatabase
import com.example.kotlinchallenge.data.network.responses.profile.ContestRatingsResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    val SOME_INTENT_FILTER_NAME = "user_name"

    private lateinit var viewModel: ProfileViewModel
    private val TAG : String = "ProfileFragment"
    var lineChartData = ArrayList<Entry>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val db = activity?.let { getDatabase(it) }
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
                    //Log.d(TAG, "onActivityCreated: Username : ${it.user_details.name}")

                    profile_name_tv.text = it.user_details?.name?.capitalize()
                    //profile_institution_tv.text = it.user_details.country
                    profile_country_tv.text = it.user_details?.country
                    profile_global_rank_tv.text = it?.global_rank
                    profile_country_rank_tv.text = it?.country_rank
                    profile_rating_tv.text = it?.rating
                    it?.contest_ratings?.let { it1 -> createLineChart(it1) }
                }
            })
        }
    }

    private val someBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val userName = intent?.getStringExtra("user_name")
            userName.let {
                no_user_found_layout.visibility = View.GONE
                profile_layout.visibility = View.VISIBLE
                viewModel.callProfileApi(it.toString())
            }
            //Log.d(TAG, "onReceive: UserName $userName")
            //intent?.getStringExtra("user_name")?.let { viewModel.callProfileApi(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(someBroadcastReceiver,
                 IntentFilter(SOME_INTENT_FILTER_NAME)
            )
        };
    }

    override fun onPause() {
        activity?.let { LocalBroadcastManager.getInstance(it).unregisterReceiver(someBroadcastReceiver) };
        super.onPause()
    }

    fun createLineChart(dataList : List<ContestRatingsResponse>){
        val entries = ArrayList<Entry>()
        for(item in dataList){
            var yAxis : Float
            yAxis = (item.getmonth.toFloat() / 100) + item.getyear.toFloat()

//            if(item.getmonth.toInt() >= 10){
//                yAxis = (item.getmonth.toFloat() / 100) + item.getyear.toFloat()
//            }else{
//                yAxis = (item.getmonth.toFloat() / 10) + item.getyear.toFloat()
//
//            }
            Log.d(TAG, "createLineChart: Month and Year $yAxis")
            Log.d(TAG, "createLineChart: Month Number ${item.getmonth}")
            Log.d(TAG, "createLineChart: Year Number ${item.getyear}")
            entries.add(Entry(yAxis,item.rating.toFloat()))

            //entries.add(Entry(item.getyear.toFloat(),item.rating.toFloat()))
        }
//        entries.add(Entry(1f, 10f))
//        entries.add(Entry(2f, 2f))
//        entries.add(Entry(3f, 7f))
//        entries.add(Entry(4f, 20f))
//        entries.add(Entry(5f, 16f))
        val vl = LineDataSet(entries, "Rating")
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorPrimary
        vl.fillAlpha = R.color.cc
        profile_line_chart.xAxis.labelRotationAngle = 0f
        profile_line_chart.data = LineData(vl)
        profile_line_chart.axisRight.isEnabled = true
        profile_line_chart.xAxis.axisMaximum = 2021f+0.1f
        profile_line_chart.setTouchEnabled(true)
        profile_line_chart.setPinchZoom(true)
        profile_line_chart.description.text = "Days"
        profile_line_chart.setNoDataText("No forex yet!")
        profile_line_chart.animateX(1800, Easing.EaseInExpo)
        val markerView = activity?.applicationContext?.let { CustomMarker(it, R.layout.marker_view) }
        profile_line_chart.marker = markerView

    }

    suspend fun insertDatabase(db : AppDatabase,ls : List<QuotesResponse>){
        withContext(Dispatchers.IO){
            db.getContestDao.insertQuotes(ls)
        }
    }

}
