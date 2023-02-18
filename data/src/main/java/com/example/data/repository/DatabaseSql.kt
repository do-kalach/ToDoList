package com.example.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.RoomTaskModel

@Database(entities = [RoomTaskModel::class], version = 1, exportSchema = false)
abstract class DatabaseSql : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}