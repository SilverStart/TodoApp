package com.silverstar.todoapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey
    val dateTime: Long,
    val title: String,
    val content: String,
    val isSuccess: Boolean
)
