package com.silverstar.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.silverstar.todoapp.data.dao.TodoDao
import com.silverstar.todoapp.data.entity.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}