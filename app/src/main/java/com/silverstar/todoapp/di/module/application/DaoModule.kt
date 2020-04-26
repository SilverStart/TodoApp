package com.silverstar.todoapp.di.module.application

import com.silverstar.todoapp.business.dao.TodoDao
import com.silverstar.todoapp.business.dao.TodoDaoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DaoModule {

    @Singleton
    @Binds
    fun todoDao(todoDao: TodoDaoImpl): TodoDao
}