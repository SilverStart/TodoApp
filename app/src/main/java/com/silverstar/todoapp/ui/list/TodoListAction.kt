package com.silverstar.todoapp.ui.list

import com.silverstar.todoapp.mvibase.MviAction

sealed class TodoListAction : MviAction {
    object LoadTodoListAction : TodoListAction()
}