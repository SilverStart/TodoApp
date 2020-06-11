package com.silverstar.todoapp.di.module.application

import android.content.Context
import androidx.room.Room
import com.silverstar.todoapp.data.TodoDatabase
import com.silverstar.todoapp.data.dao.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao = todoDatabase.todoDao()
}