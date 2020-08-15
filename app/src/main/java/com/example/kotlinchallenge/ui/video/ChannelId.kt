package com.example.kotlinchallenge.ui.video

/**
 * Created by Venkhatesh on 15-08-2020.
 */
class ChannelId {

    companion object {
        lateinit var chId : String
        fun setChannelId(id : String){
            chId = id
        }

        fun getChannelId() = chId
    }
}