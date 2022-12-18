package com.example.task_app.ui.fragments.buy_list.model

data class ToBuyResponseItem(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)