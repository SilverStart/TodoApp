package com.silverstar.todoapp.di.module

import androidx.lifecycle.ViewModel
import com.silverstar.todoapp.business.interactor.TodoInputInteractor
import com.silverstar.todoapp.di.ViewModelBuilder
import com.silverstar.todoapp.di.ViewModelKey
import com.silverstar.todoapp.mvibase.MviInteractor
import com.silverstar.todoapp.ui.input.TodoInputAction
import com.silverstar.todoapp.ui.input.TodoInputActivity
import com.silverstar.todoapp.ui.input.TodoInputResult
import com.silverstar.todoapp.ui.input.TodoInputViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TodoInputModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun todoInputActivity(): TodoInputActivity

    @Binds
    abstract fun bindInteractor(todoInputInteractor: TodoInputInteractor): MviInteractor<TodoInputAction, TodoInputResult>

    @Binds
    @IntoMap
    @ViewModelKey(TodoInputViewModel::class)
    abstract fun bindViewModel(todoInputViewModel: TodoInputViewModel): ViewModel
}