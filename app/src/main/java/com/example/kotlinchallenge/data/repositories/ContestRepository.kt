package com.example.kotlinchallenge.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinchallenge.data.network.CodeChefApi
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.DataResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Venkhatesh on 02-06-2020.
 */
class ContestRepository {
    val TAG:String = "ContestRepository"

    fun fetchOngoingContest(contestName : String) : LiveData<List<ArrayDataResponse>> {
        var arrayResult = MutableLiveData<List<ArrayDataResponse>>()
        lateinit var contestCall : Call<DataResponse>
        if (contestName.equals("upcoming")){
           // contestCall = CodeChefApi.invoke().upComing()
        }else {
            contestCall = CodeChefApi.invoke().onGoing()
        }
            contestCall.enqueue(object : Callback<DataResponse>{

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
               //Apiresponse.value = t.message
                Log.d(TAG,t.message)
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    arrayResult.value = response.body()?.Data!!
                    //Apiresponse.value = response.body().toString()
                    Log.d(TAG,response.body()?.Data?.get(0)?.Name)
                    Log.d(TAG,"Repository List " + arrayResult.value!!.size)

                }else{
                    //Apiresponse.value = response.errorBody()?.string()
                    Log.d(TAG,response.errorBody()?.string())

                }
            }
        })
       Log.d(TAG,"Repository List " + arrayResult.value?.size)
       return arrayResult
    }




    suspend fun fetchUpcomingContest(): List<ArrayDataResponse>? {
        val result = CodeChefApi.invoke().upComing()
        val apiResponse = MutableLiveData<List<ArrayDataResponse>>()
        //apiResponse.value = result.body()?.Data
        return result.body()?.Data
    }
}