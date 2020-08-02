package com.example.kotlinchallenge.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.kotlinchallenge.NotifyWorker
import com.example.kotlinchallenge.NotifyWorker.Companion.NOTIFICATION_ID
import com.example.kotlinchallenge.NotifyWorker.Companion.NOTIFICATION_WORK
import com.example.kotlinchallenge.R
import com.google.android.material.snackbar.Snackbar.make
import java.lang.System.currentTimeMillis
import java.nio.charset.CodingErrorAction.REPLACE
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*
import java.util.Locale.getDefault
import java.util.concurrent.TimeUnit

/**
 * Created by Venkhatesh on 02-06-2020.
 */

val TAG: String = "ViewUtils"

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
    val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo=connectivityManager.activeNetworkInfo
    return  networkInfo!=null && networkInfo.isConnected
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
private fun Context.scheduleNotification(delay: Long, data: Data) {
    val notificationWork = OneTimeWorkRequest.Builder(NotifyWorker::class.java)
        .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()
    val instanceWorkManager = WorkManager.getInstance(this)
    instanceWorkManager.enqueue(notificationWork)
}

//private fun Context.scheduleQuoteNotification(){
//    val saveRequest = PeriodicWorkRequestBuilder<NotifyWorker::class.java>(1, TimeUnit.HOURS)
//            // Additional configuration
//            .build()
//    val instanceWorkManager = WorkManager.getInstance(this)
//    instanceWorkManager.enqueue(notificationWork)
//}

@SuppressLint("WrongConstant")
fun Context.setNotification(year:Int, month:Int, day:Int, hour:Int, minute:Int){
    var customCalendar = Calendar.getInstance()
    //customCalendar.set(year,month,day,hour,minute,0)
    customCalendar.set(year,month-1,day,hour,minute,0)

    val customTime = customCalendar.time.time
    val currentTime = currentTimeMillis()

    val differenceOne = customTime - currentTime
    val differenceTwo = currentTime - customTime
    Log.d(TAG, "setNotification: ")
    if (customTime > currentTime) {
        val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
        val delay = customTime - currentTime
        Log.d(TAG,"Current TIme $currentTime " + currentTime.toString().length)
        Log.d(TAG,"Custom Time $customTime " + customTime.toString().length)
        Log.d(TAG ,"Delay Time "+ delay.toString() + " ${delay.toString().length}")
        scheduleNotification(delay, data)
        Log.d(TAG,"Notif Sucessfull")
    } else {
        Log.d(TAG,"Notif Unsucessfull")
    }
}


fun Context.getMonthNumber(month: String) : Int{
    var monthLower = month.toLowerCase()
    Log.d(TAG, "getMonthNumber: $monthLower")
    if (monthLower.contains("jan")){
        return 1
    }else if (monthLower.contains("feb")){
        return 2
    }else if(monthLower.contains("mar")){
        return 3
    }else if(monthLower.contains("apr")){
        return 4
    }else if(monthLower.contains("may")){
        return 5
    }else if(monthLower.contains("jun")){
        return 6
    }else if(monthLower.contains("jul")){
        return 7
    }else if(monthLower.contains("aug")){
        return 8
    }else if(monthLower.contains("sep")){
        return 9
    }else if(monthLower.contains("oct")){
        return 10
    }else if(monthLower.contains("nov")){
        return 11
    }else{
        return 12
    }

}