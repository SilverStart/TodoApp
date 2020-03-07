package com.silverstar.todoapp.ui.list

import com.silverstar.todoapp.mvibase.MviIntent

sealed class TodoListIntent : MviIntent {
    object LoadTodoListIntent : TodoListIntent()
}