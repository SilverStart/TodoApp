package com.silverstar.todoapp.di.component

import android.content.Context
import com.silverstar.todoapp.TodoApplication
import com.silverstar.todoapp.di.module.TodoListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TodoListModule::class]
)
interface ApplicationComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}