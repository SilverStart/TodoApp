package com.silverstar.todoapp.util

data class Event<T>(
    private val content: T
) {
    var isHandled: Boolean = false
        private set

    fun getIfNotHandled(): T? {
        if (isHandled) return null

        isHandled = true
        return content
    }
}