package com.silverstar.todoapp.di.component

import android.content.Context
import com.silverstar.todoapp.TodoApplication
import com.silverstar.todoapp.di.module.TodoInputModule
import com.silverstar.todoapp.di.module.TodoListModule
import com.silverstar.todoapp.di.module.application.DaoModule
import com.silverstar.todoapp.di.module.application.SchedulerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DaoModule::class,
        SchedulerModule::class,
        TodoListModule::class,
        TodoInputModule::class]
)
interface ApplicationComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}