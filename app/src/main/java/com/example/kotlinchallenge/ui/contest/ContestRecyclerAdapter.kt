package com.example.kotlinchallenge.ui.contest

import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.util.getMonthNumber
import com.example.kotlinchallenge.util.inflate
import com.example.kotlinchallenge.util.setNotification
import kotlinx.android.synthetic.main.contest_item.view.*


/**
 * Created by Venkhatesh on 06-06-2020.
 */
class ContestRecyclerAdapter(val contestList:List<ArrayDataResponse>, val contestType:String): RecyclerView.Adapter<ContestRecyclerAdapter.ContestViewHolder>() {
    val TAG : String = "ContestRecyclerAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val inflatedView =parent.inflate(R.layout.contest_item,false)
        return ContestViewHolder(inflatedView,contestType)
    }

    override fun getItemCount(): Int = contestList.size

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int)  {
            val itemContest = contestList.get(position)
            if (itemContest != null) {
                holder.bindContest(itemContest,contestType)
            }

    }

    class ContestViewHolder(v:View, contest: String):RecyclerView.ViewHolder(v), View.OnClickListener {
        val TAG : String = "ContestRecyclerAdapter"
        private var view: View = v
            private var contest: ArrayDataResponse? = null
        private var contestType : String? = null
        init {
            v.setOnClickListener(this)
        }

        fun bindContest(contest:ArrayDataResponse,contestType: String){
                this.contest = contest
                this.contestType = contestType
                itemView.contest_name_tv.text = contest.Name
                itemView.contest_code_tv.text = contest.Code
                itemView.contest_start_tv.text = contest.Start
                itemView.contest_end_tv.text = contest.End
                if (!contest.Code.equals("Code")){
                    var timeStamp = contest.Start
                    Log.d(TAG, "bindContest: Real Time Stamp $timeStamp")
                    var timeDateStampSplit = timeStamp.split(" ")
                    var timeSplit = timeDateStampSplit[4].split(':')
                    //Log.d(TAG,"Time " + timeSplit)
                    var day = timeDateStampSplit[0].toInt()
                    var month = itemView.context.getMonthNumber(timeDateStampSplit[1])
                    var year = timeDateStampSplit[2].toInt()
                    var hour = timeSplit[0].toInt()
                    var minutes = timeSplit[1].toInt()
                    var secs = timeSplit[2].toInt()
                    //Log.d(TAG,"Month " + itemView.context.getMonthNumber(timeDateStampSplit[1]))
                    itemView.contest_notif_iv.setOnClickListener(View.OnClickListener {
//                        Log.d(TAG, "Inside Notif")
                        Log.d(TAG,"Onclick Date " + day + " " + month + " " + year)
                        Log.d(TAG, "Onclick Time Stamp " + hour + " " + minutes + " " + secs)
                        it.context.setNotification(year,month,day,hour,minutes)
                        Glide.with(itemView.context).load("").placeholder(R.drawable.ic_baseline_notifications_24).into(itemView.contest_notif_iv)
                        Toast.makeText(it.context,"Notification added successfully!",Toast.LENGTH_LONG).show()
                    })
                }
                if (contestType.equals("ongoing")){
                    itemView.contest_notif_iv.visibility = View.GONE
                }
                if(contestType.equals("past")){
                    itemView.contest_notif_iv.visibility = View.GONE
                    

                }
        }


        override fun onClick(v: View?) {
            Log.d(TAG,"ClickListener")
            Log.d(TAG,"ID " + v?.id.toString())
            Log.d(TAG, "onClick: URL ${contest?.Url}")
            Log.d(TAG, "onClick: Contest Name $contestType")
            if(contestType?.equals("past")!!){
                var url = "https://www.codechef.com/${contest!!.Url}"
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(itemView.context, Uri.parse(url))
            }
            if (v != null) {
                if (v.id == R.id.contest_notif_iv){
                    Log.d(TAG,"Bell Icon Click")
                    //   v.context.setNotification(2020,6,7,16,20)
                }
            }
            if (itemView.contest_time_layout.visibility == View.VISIBLE){
                itemView.contest_time_layout.visibility = View.GONE
            }else{
                itemView.contest_time_layout.visibility = View.VISIBLE
            }
        }
    }
}



