package com.example.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.RoomTaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(taskModel: RoomTaskModel)

    @Delete
    fun delete(taskModel: RoomTaskModel)

    @Query("SELECT * FROM tasks WHERE field=:task")
    fun getTask(task: String): RoomTaskModel

    @Query("SELECT * FROM tasks")
    fun getTasks(): List<RoomTaskModel>

}