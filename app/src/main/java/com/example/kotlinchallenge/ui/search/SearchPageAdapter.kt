package com.example.kotlinchallenge.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by Venkhatesh on 17-08-2020.
 */
class SearchPageAdapter(fragmentManager:FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> {
                return UserFavouriteFragment()
            }
            1->{
                return ProblemFavouriteFragment()
            }
        }
        return UserFavouriteFragment()
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0-> {
                return "Users"
            }
            1->{
                return "Problems"
            }
        }
        return "Users"
    }

    override fun getCount(): Int {
        return 2
    }
}