package com.example.kotlinchallenge.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinchallenge.R
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Venkhatesh on 06-08-2020.
 */

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottie_test.setAnimation("price_tag.json")
        lottie_test.playAnimation()
        lottie_test.loop(true)
    }
}