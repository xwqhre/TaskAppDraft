package com.example.taskappdraft.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskappdraft.model.TaskModel

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskModel")
    fun getAll(): List<TaskModel>

    @Insert
    fun insert(taskModel: TaskModel)

    @Delete
    fun delete(taskModel: TaskModel)

    @Update
    fun update(taskModel: TaskModel)

}
