package com.example.model.repository

import com.example.model.model.TaskModel

interface Task {
    fun save(task: TaskModel)
    fun getTask(task: String): TaskModel
    fun delete(task: TaskModel)
    fun getTasks(): List<TaskModel>
}