package com.silverstar.todoapp.ui.list

import com.silverstar.todoapp.business.entity.Todo
import com.silverstar.todoapp.mvibase.MviResult

sealed class TodoListResult : MviResult {
    object IsLoading : TodoListResult()
    sealed class LoadTodoListResult : TodoListResult() {
        data class Success(val todoList: List<Todo>) : LoadTodoListResult()
    }
}