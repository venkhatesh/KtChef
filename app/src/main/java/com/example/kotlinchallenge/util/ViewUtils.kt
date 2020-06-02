package com.example.kotlinchallenge.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Venkhatesh on 02-06-2020.
 */

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}