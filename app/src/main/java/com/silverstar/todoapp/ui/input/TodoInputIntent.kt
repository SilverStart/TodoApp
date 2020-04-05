package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.mvibase.MviIntent

sealed class TodoInputIntent : MviIntent {
    data class SaveIntent(val title: String, val content: String) : TodoInputIntent()
}