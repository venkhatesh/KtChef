package com.example.kotlinchallenge.ui.contest

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.util.inflate
import kotlinx.android.synthetic.main.contest_item.view.*

/**
 * Created by Venkhatesh on 06-06-2020.
 */
class ContestRecyclerAdapter(val contestList:List<ArrayDataResponse>): RecyclerView.Adapter<ContestRecyclerAdapter.ContestViewHolder>() {
    val TAG : String = "ContestRecyclerAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val inflatedView =parent.inflate(R.layout.contest_item,false)
        return ContestViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = contestList.size

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int)  {
        val itemContest = contestList.get(position)
        if(itemContest != null){
            holder.bindContest(itemContest)
        }
    }

    class ContestViewHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {
        val TAG : String = "ContestRecyclerAdapter"
        private var view: View = v
            private var contest: ArrayDataResponse? = null
        init {
            v.setOnClickListener(this)
        }

        fun bindContest(contest:ArrayDataResponse){
            this.contest = contest
            itemView.contest_name_tv.text = contest.Name
            itemView.contest_code_tv.text = contest.Code
            itemView.contest_start_tv.text = contest.Start
            itemView.contest_end_tv.text = contest.End
        }

        override fun onClick(v: View?) {
            Log.d(TAG,"ClickListener")
            if (itemView.contest_time_layout.visibility == View.VISIBLE){
                itemView.contest_time_layout.visibility = View.GONE
            }else{
                itemView.contest_time_layout.visibility = View.VISIBLE
            }
        }
    }
}