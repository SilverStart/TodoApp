package com.silverstar.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.silverstar.todoapp.data.entity.Todo
import io.reactivex.Observable

@Dao
interface TodoDao {

    @Insert
    fun insert(vararg todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAll(): Observable<List<Todo>>
}