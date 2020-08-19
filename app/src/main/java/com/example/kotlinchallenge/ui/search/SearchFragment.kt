package com.example.kotlinchallenge.ui.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.db.getDatabase
import com.example.kotlinchallenge.data.repositories.ProfileRepository
import com.example.kotlinchallenge.ui.profile.ProfileViewModel
import com.example.kotlinchallenge.ui.profile.ProfileViewModelFactory
import com.google.android.material.slider.Slider
import com.google.android.material.tabs.TabLayout
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch
import com.llollox.androidtoggleswitch.widgets.ToggleSwitchButton
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.user_item.*


/**
 * Created by Venkhatesh on 06-08-2020.
 */

class SearchFragment : Fragment(), View.OnClickListener {

    val TAG = "SearchFragment"
    lateinit var pagerAdapter: SearchPageAdapter
    lateinit var tabLayout : TabLayout
    var whichSearch = "user"
    private lateinit var viewModel: ProfileViewModel

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
        search_tv.setOnClickListener(this)
        search_profile.setBackgroundColor(Color.parseColor("#ffffff"))
        val viewPager = activity?.findViewById<ViewPager>(R.id.search_view_pager)
        pagerAdapter = SearchPageAdapter(requireActivity().supportFragmentManager)
        viewPager?.adapter = pagerAdapter
        tabLayout = activity?.findViewById(R.id.search_tab_layout)!!
        tabLayout.setupWithViewPager(viewPager)
        val db = activity?.let { getDatabase(it) }
        val repository  = db?.let { ProfileRepository(it) }
        val modelFactory = repository?.let { ProfileViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this,modelFactory).get(ProfileViewModel::class.java)
        viewModel.liveResult.observe(requireActivity(), Observer {
                user_item_name.text = it.user_details.name
                user_item_user_name.text = it.user_details.username
                user_item_country_tv.text = it.country_rank
                user_item_global_tv.text = it.global_rank
                user_item_codechef_tv.text = it.rating
        })

        viewModel.loading.observe(requireActivity(), Observer {
            if(it){
                light.visibility = View.VISIBLE
                light.on()
                user_item_ll.visibility = View.GONE
            }else{
                light.visibility = View.GONE
                light.off()
                user_item_ll.visibility = View.VISIBLE
            }
        })
        //light.on()

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
                 whichSearch = "user"
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
                whichSearch = "tags"

            }
            R.id.search_tv -> {
                if (whichSearch.equals("user")){
                    viewModel.callProfileApi(search_box_et.text.toString())
                }
            }
        }
    }

}




