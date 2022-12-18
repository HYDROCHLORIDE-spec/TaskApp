package com.example.task_app.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.task_app.data.local.room.enitities.ToSellEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToSellDao {
    @Query("Select * from ItemToSell")
    fun itemToSell(): Flow<List<ToSellEntity>>
}