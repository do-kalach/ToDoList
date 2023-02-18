package com.example.data.mapper

import com.example.data.model.RoomTaskModel
import com.example.model.model.TaskModel

class RoomMapperFromDomToData : Mapper<TaskModel, RoomTaskModel> {
    override fun map(data: TaskModel): RoomTaskModel {
        return RoomTaskModel(
            field = data.name
        )
    }

}