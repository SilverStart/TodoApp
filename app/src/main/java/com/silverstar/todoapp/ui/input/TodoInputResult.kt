package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.mvibase.MviResult

sealed class TodoInputResult : MviResult {
    object IsLoading : TodoInputResult()
    sealed class SaveTodoResult : TodoInputResult() {
        object Success : SaveTodoResult()
        data class Fail(val todoInputError: TodoInputError) : SaveTodoResult()
    }
}