package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.mvibase.MviViewState
import com.silverstar.todoapp.util.Event

data class TodoInputViewState(
    val isLoading: Boolean,
    val isSuccess: Event<Boolean>,
    val error: Event<TodoInputError>
) : MviViewState {

    companion object {
        fun idle(): TodoInputViewState = TodoInputViewState(
            false,
            Event(false),
            Event(TodoInputError.NONE)
        )
    }
}