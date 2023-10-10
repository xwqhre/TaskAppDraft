package com.example.taskappdraft

import android.app.Application
import androidx.room.Room
import com.example.taskappdraft.data.local.db.AppDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        dp = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "dataBaseName"
        ).allowMainThreadQueries().build()
    }

    companion object{
        lateinit var dp: AppDataBase
    }
}