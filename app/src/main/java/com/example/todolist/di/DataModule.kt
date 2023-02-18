package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.data.repository.DatabaseSql
import com.example.data.repository.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DatabaseSql =
        Room.databaseBuilder(context, DatabaseSql::class.java, "task.db")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesTaskDao(databaseSql: DatabaseSql): TaskDao = databaseSql.taskDao()
}