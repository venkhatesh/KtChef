package com.example.kotlinchallenge

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.ui.contest.Contest
import com.example.kotlinchallenge.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.nio.charset.CodingErrorAction
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(),NetworkListener {

    lateinit var Contest: Contest
    lateinit var profileFragment: ProfileFragment
    var TAG : String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        val BottomNavigation : BottomNavigationView = findViewById(R.id.btm_nav)

        Contest = Contest()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout,Contest)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        BottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_contest->{
                    Contest = Contest()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout,Contest)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_profile->{
                    profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout,profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }

    }

    override fun onStarted() {
        Log.d(TAG, "onStarted: ")
    }

    override fun onSuccess(onGoingResponse: List<ArrayDataResponse>) {
        Log.d(TAG, "onSuccess: ")
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: ")
    }
}
