package com.example.task_app.ui.activities.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_app.data.repository.Repository
import com.example.task_app.data.network.ApiResultSealed
import com.example.task_app.data.local.room.enitities.ToSellEntity
import com.example.task_app.ui.fragments.buy_list.model.ToBuyResponse
import com.example.task_app.ui.fragments.call_list.model.ToCallResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val buyResponseLivedata: LiveData<ApiResultSealed<ToBuyResponse>>
        get() = repository.buyResponseLiveData
    val callResponseLivedata: LiveData<ApiResultSealed<ToCallResponse>>
        get() = repository.callResponseLiveData
    private val toSellFlowResponseLiveData = MutableLiveData<List<ToSellEntity>>()
    val toSellResponseLiveData: LiveData<List<ToSellEntity>>
        get() = toSellFlowResponseLiveData


    fun getBuyData() {
        viewModelScope.launch {
            repository.getBuyData()
        }
    }

    fun getToSellData() {
        viewModelScope.launch {
            repository.getToSellData().collect {
                toSellFlowResponseLiveData.postValue(it)
            }
        }
    }

    fun getCallData() {
        viewModelScope.launch {
            repository.getCallData()
        }
    }
}