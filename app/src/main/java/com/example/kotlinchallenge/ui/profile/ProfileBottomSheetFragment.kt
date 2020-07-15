package com.example.kotlinchallenge.ui.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.kotlinchallenge.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.user_name_bottom_sheet.*


/**
 * Created by Venkhatesh on 15-07-2020.
 */
class ProfileBottomSheetFragment : BottomSheetDialogFragment(){

    var TAG = "ProfileBottomSheetFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.user_name_bottom_sheet,container,false)
        var user_name_tv = view.findViewById<Button>(R.id.user_name_submit_btn)
        user_name_tv.setOnClickListener(View.OnClickListener {
            var user_name_edit = view.findViewById<EditText>(R.id.user_name_et)
            var userName = user_name_edit.text
            Log.d(TAG, "onCreateView: Username " + userName)
            if (userName!=null){
                val pref: SharedPreferences? = activity?.getSharedPreferences("user_name", 0)  // 0 - for private mode
                val editor: SharedPreferences.Editor? = pref?.edit()
                if (editor != null) {
                    editor.putString("user_name", userName.toString())
                    editor.commit()
                    dismiss()
                }
            }
        })
        return view
    }
}