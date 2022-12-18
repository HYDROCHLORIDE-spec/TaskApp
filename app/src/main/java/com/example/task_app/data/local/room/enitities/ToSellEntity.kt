package com.example.task_app.data.local.room.enitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class ToSellEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "quantity") val quantity: Int?,
    @ColumnInfo(name = "type") val type: Int?
)
