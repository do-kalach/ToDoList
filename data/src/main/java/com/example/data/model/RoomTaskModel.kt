package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class RoomTaskModel(
    @PrimaryKey
    val field: String
)