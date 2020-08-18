package com.example.kotlinchallenge.ui.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kotlinchallenge.R
import com.google.android.material.slider.Slider
import com.google.android.material.tabs.TabLayout
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch
import com.llollox.androidtoggleswitch.widgets.ToggleSwitchButton
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by Venkhatesh on 06-08-2020.
 */

class SearchFragment : Fragment(), View.OnClickListener {

    val TAG = "SearchFragment"
    lateinit var pagerAdapter: SearchPageAdapter
    lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottie_animation.setAnimation("profile.json")
        lottie_animation.playAnimation()
        lottie_animation.loop(false)
        search_profile.setOnClickListener(this)
        search_tags.setOnClickListener(this)
        search_profile.setBackgroundColor(Color.parseColor("#ffffff"))
        val viewPager = activity?.findViewById<ViewPager>(R.id.search_view_pager)
        pagerAdapter = SearchPageAdapter(requireActivity().supportFragmentManager)
        viewPager?.adapter = pagerAdapter
        tabLayout = activity?.findViewById(R.id.search_tab_layout)!!
        tabLayout.setupWithViewPager(viewPager)
        light.on()

    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {
        Log.d(TAG, "onClick: CLICK CLICK")
        when(v?.id){
             R.id.search_profile -> {
                 Log.d(TAG, "onClick: Profile CLick CLick")
                 search_tags.setBackgroundColor(Color.parseColor("#ffffff"))
                 search_tags.setTextColor(Color.parseColor("#5a4536"))
                 search_profile.setBackgroundColor(Color.parseColor("#5a4536"))
                 search_profile.setTextColor(Color.parseColor("#ffffff"))
                 lottie_animation.setAnimation("profile.json")
                 lottie_animation.playAnimation()
                 lottie_animation.loop(false)
                 search_box_et.setHint(R.string.search_profile)
             }
            R.id.search_tags -> {
                Log.d(TAG, "onClick: Tags Click Click")
                search_profile.setBackgroundColor(Color.parseColor("#ffffff"))
                search_profile.setTextColor(Color.parseColor("#5a4536"))
                search_tags.setBackgroundColor(Color.parseColor("#5a4536"))
                search_tags.setTextColor(Color.parseColor("#ffffff"))
                lottie_animation.setAnimation("price_tag.json")
                lottie_animation.playAnimation()
                lottie_animation.loop(false)
                search_box_et.setHint(R.string.search_tag)

            }
        }
    }

}




