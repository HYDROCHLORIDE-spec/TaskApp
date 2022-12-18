package com.example.task_app.data.network

import com.example.task_app.ui.fragments.buy_list.model.ToBuyResponse
import com.example.task_app.ui.fragments.call_list.model.ToCallResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("call")
    suspend fun call(): Response<ToCallResponse>
    @GET("buy")
    suspend fun buy(): Response<ToBuyResponse>
}