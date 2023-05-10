package com.example.taskappdraft.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskappdraft.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun taskDao() : TaskDao
}