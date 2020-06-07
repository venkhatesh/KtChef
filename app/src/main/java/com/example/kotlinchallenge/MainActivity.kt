package com.example.kotlinchallenge

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.kotlinchallenge.ui.contest.Contest
import com.example.kotlinchallenge.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.nio.charset.CodingErrorAction
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var Contest: Contest
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar

            setSupportActionBar(toolbar)
            supportActionBar?.setTitle("Code Chef")

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
}
