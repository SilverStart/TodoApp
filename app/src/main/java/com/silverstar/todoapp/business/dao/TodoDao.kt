package com.silverstar.todoapp.business.dao

import com.silverstar.todoapp.business.entity.Todo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface TodoDao {

    fun getAll(): Observable<List<Todo>>

    fun insert(todo: Todo)
}

class TodoDaoImpl @Inject constructor() : TodoDao {
    override fun getAll(): Observable<List<Todo>> {
        return Observable.just(emptyList())
    }

    override fun insert(todo: Todo) {

    }
}