package com.example.kotlinchallenge.ui.contest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Venkhatesh on 02-06-2020.
 */
class ContestPageAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return OngoingFragment()
            }
            1->{
                return UpcomingFragment()
            }
            2->{
                return PastFragment()
            }
        }
        return OngoingFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->{
                return "Ongoing"
            }
            1->{
                return "Upcoming"
            }
            2->{
                return "Past"
            }
        }
        return "Ongoing"
    }

    override fun getCount(): Int {
        return 3
    }
}