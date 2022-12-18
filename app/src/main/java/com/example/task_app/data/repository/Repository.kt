package com.example.task_app.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task_app.R
import com.example.task_app.data.network.ApiResultSealed
import com.example.task_app.data.network.Api
import com.example.task_app.data.local.room.dao.ToSellDao
import com.example.task_app.ui.fragments.buy_list.model.ToBuyResponse
import com.example.task_app.ui.fragments.call_list.model.ToCallResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val context: Context,
    private val api: Api,
    private val toSellDao: ToSellDao
) {
    private val buyRepositoryResponseLiveData = MutableLiveData<ApiResultSealed<ToBuyResponse>>()
    private val callRepositoryResponseLiveData = MutableLiveData<ApiResultSealed<ToCallResponse>>()
    val buyResponseLiveData: LiveData<ApiResultSealed<ToBuyResponse>>
        get() = buyRepositoryResponseLiveData
    val callResponseLiveData: LiveData<ApiResultSealed<ToCallResponse>>
        get() = callRepositoryResponseLiveData

    suspend fun getBuyData() {
        buyRepositoryResponseLiveData.postValue(ApiResultSealed.Loading())
        val response = api.buy()
        if (response.isSuccessful) {
            buyRepositoryResponseLiveData.postValue(ApiResultSealed.Success(response.body()))
        } else {
            buyRepositoryResponseLiveData.postValue(
                ApiResultSealed.Error(
                    context.getString(R.string.something_went_wrong),
                    null
                )
            )
        }
    }

    suspend fun getCallData() {
        callRepositoryResponseLiveData.postValue(ApiResultSealed.Loading())
        val response = api.call()
        if (response.isSuccessful) {
            callRepositoryResponseLiveData.postValue(ApiResultSealed.Success(response.body()))
        } else {
            callRepositoryResponseLiveData.postValue(
                ApiResultSealed.Error(
                    context.getString(R.string.something_went_wrong),
                    null
                )
            )
        }
    }

    fun getToSellData() = toSellDao.itemToSell()
}