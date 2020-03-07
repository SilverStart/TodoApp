package com.silverstar.todoapp.ui.list

import com.silverstar.todoapp.business.entity.Todo
import com.silverstar.todoapp.mvibase.MviViewState

data class TodoListViewState(
    val isLoading: Boolean,
    val todoList: List<Todo>
) : MviViewState {

    companion object {
        fun idle(): TodoListViewState = TodoListViewState(false, emptyList())
    }
}