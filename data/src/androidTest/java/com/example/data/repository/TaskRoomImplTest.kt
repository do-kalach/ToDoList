package com.example.data.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.model.model.TaskModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TaskRoomImplTest {

    val context = InstrumentationRegistry.getInstrumentation().targetContext
    private var dataBase: DatabaseSql? = null
    private var dao: TaskDao? = null
    private var taskRoomImpl: TaskRoomImpl? = null

    @Before
    fun provideData() {
        dataBase =
            Room.inMemoryDatabaseBuilder(context, DatabaseSql::class.java).allowMainThreadQueries()
                .build()
        dao = dataBase?.taskDao()
        taskRoomImpl = TaskRoomImpl(dao!!)
    }

    @Test
    fun insertAndGetDatabase() {
        val save = TaskModel(name = "hello")
        taskRoomImpl?.save(save)
        val get = taskRoomImpl?.getTask("hello")
        Assert.assertEquals(save, get)
    }

    @Test
    fun insetMultiplyDataToDatabase() {
        val dat1 = TaskModel(name = "dat1")
        val dat2 = TaskModel(name = "dat2")
        val dat3 = TaskModel(name = "dat3")
        taskRoomImpl?.save(dat1)
        taskRoomImpl?.save(dat2)
        taskRoomImpl?.save(dat3)
        val getTasks = taskRoomImpl?.getTasks()!!
        Assert.assertEquals(dat1, getTasks[0])
        Assert.assertEquals(dat2, getTasks[1])
        Assert.assertEquals(dat3, getTasks[2])
    }

    @Test
    fun noData() {
        val actual = taskRoomImpl?.getTasks()!!
        Assert.assertEquals(emptyList<TaskModel>(), actual)
    }

    @After
    fun afterTest() {
        dataBase = null
    }
}