package com.example.task_app.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_app.data.local.room.dao.ToSellDao
import com.example.task_app.data.local.room.enitities.ToSellEntity

@Database(entities = [ToSellEntity::class], version = 1, exportSchema = true)
abstract class ToSellDatabase : RoomDatabase() {
    abstract fun toSellDao(): ToSellDao
}