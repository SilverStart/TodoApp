package com.silverstar.todoapp.di.module.application

import com.silverstar.todoapp.util.SchedulerProvider
import com.silverstar.todoapp.util.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SchedulerModule {

    @Singleton
    @Binds
    fun bindSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): SchedulerProvider
}