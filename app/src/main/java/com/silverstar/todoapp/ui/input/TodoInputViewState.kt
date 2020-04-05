package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.mvibase.MviViewState
import com.silverstar.todoapp.util.Event

data class TodoInputViewState(
    val isLoading: Boolean,
    val isEmptyTitle: Event<Boolean>,
    val isSuccess: Event<Boolean>
) : MviViewState {

    companion object {
        fun idle(): TodoInputViewState = TodoInputViewState(
            false,
            Event(false),
            Event(false)
        )
    }
}