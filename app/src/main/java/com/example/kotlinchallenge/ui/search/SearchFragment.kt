package com.example.kotlinchallenge.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinchallenge.R
import com.google.android.material.slider.Slider
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch
import com.llollox.androidtoggleswitch.widgets.ToggleSwitchButton
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by Venkhatesh on 06-08-2020.
 */

class SearchFragment : Fragment(), ToggleSwitch.OnChangeListener {

    val TAG = "SearchFragment"
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
        val test = activity?.findViewById<ToggleSwitchButton>(R.id.toggle_switch_btn)
        Log.d(TAG, "onViewCreated: Test Test")
        Log.d(TAG, "onViewCreated: ")
        toggle_switch_btn.setOnClickListener(View.OnClickListener {
            Log.d(TAG, "onViewCreated: ")
        })
        toggle_switch_btn.setOnTouchListener(View.OnTouchListener { v, event ->
           Log.d(TAG, "onViewCreated: insdieeee")
           true
           
       })
        test?.setOnClickListener {
            Log.d(TAG, "onViewCreated: TEST TEST")
        }
        toggle_switch_btn.setOnClickListener {
            Log.d(TAG, "onViewCreated: INSIDE")
        }
//        toggle_switch_btn.setOnToggleSwitchChangeListener { position, isChecked ->
//            // Write your code ...
//            Log.d(TAG, "onViewCreated: POsition CHeck $position")
//        }

    }

    override fun onToggleSwitchChanged(position: Int) {
        Log.d(TAG, "onToggleSwitchChanged: AIYO")
    }
}


