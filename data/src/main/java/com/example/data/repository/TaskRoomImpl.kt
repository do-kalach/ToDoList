package com.example.data.repository

import com.example.data.mapper.RoomMapperFromDataToDom
import com.example.data.mapper.RoomMapperFromDomToData
import com.example.model.model.TaskModel
import com.example.model.repository.Task

class TaskRoomImpl(private val dao: TaskDao) : Task {

    private val mapperFromDom = RoomMapperFromDomToData()
    private val mapperFromData = RoomMapperFromDataToDom()

    override fun save(task: TaskModel) {
        val data = mapperFromDom.map(task)
        dao.insert(data)
    }

    override fun getTask(task: String): TaskModel {
        val data = dao.getTask(task)
        return mapperFromData.map(data)
    }

    override fun delete(task: TaskModel) {
        val data = mapperFromDom.map(task)
        dao.delete(data)
    }

    override fun getTasks(): List<TaskModel> {
        return dao.getTasks().map {
            mapperFromData.map(it)
        }
    }

}