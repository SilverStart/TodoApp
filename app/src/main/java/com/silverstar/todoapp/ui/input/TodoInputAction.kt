package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.mvibase.MviAction

sealed class TodoInputAction : MviAction {
    data class SaveTodoAction(val title: String, val content: String) : TodoInputAction()
}