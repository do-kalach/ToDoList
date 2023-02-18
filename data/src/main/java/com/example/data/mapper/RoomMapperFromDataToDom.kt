package com.example.data.mapper

import com.example.data.model.RoomTaskModel
import com.example.model.model.TaskModel

class RoomMapperFromDataToDom : Mapper<RoomTaskModel, TaskModel> {
    override fun map(data: RoomTaskModel): TaskModel {
        return TaskModel(
            data.field
        )
    }

}