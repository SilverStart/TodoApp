package com.silverstar.todoapp.di.module

import androidx.lifecycle.ViewModel
import com.silverstar.todoapp.business.dao.TodoDao
import com.silverstar.todoapp.business.dao.TodoDaoImpl
import com.silverstar.todoapp.di.ViewModelBuilder
import com.silverstar.todoapp.di.ViewModelKey
import com.silverstar.todoapp.ui.list.TodoListActivity
import com.silverstar.todoapp.ui.list.TodoListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TodoListModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun todoListActivity(): TodoListActivity

    @Binds
    @IntoMap
    @ViewModelKey(TodoListViewModel::class)
    internal abstract fun bindViewModel(viewModel: TodoListViewModel): ViewModel
}