package com.silverstar.todoapp.business.interactor

import com.silverstar.todoapp.business.dao.TodoDao
import com.silverstar.todoapp.business.entity.Todo
import com.silverstar.todoapp.mvibase.MviInteractor
import com.silverstar.todoapp.ui.input.TodoInputAction
import com.silverstar.todoapp.ui.input.TodoInputResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

open class TodoInputInteractor @Inject constructor(
    private val todoDao: TodoDao
) :
    MviInteractor<TodoInputAction, TodoInputResult> {
    override val actionProcessor =
        ObservableTransformer<TodoInputAction, TodoInputResult> { actions ->
            actions.publish {
                it.ofType(TodoInputAction.SaveTodoAction::class.java)
                    .compose(saveTodo)
            }
        }

    private val saveTodo =
        ObservableTransformer<TodoInputAction.SaveTodoAction, TodoInputResult> { action ->
            action.flatMap {
                Observable
                    .fromCallable { todoDao.insert(Todo(it.title, it.content, false)) }
                    .map { TodoInputResult.SaveTodoResult.Success }
                    .cast(TodoInputResult::class.java)
                    .startWith(Observable.just(TodoInputResult.IsLoading))
                    .onErrorReturn { TodoInputResult.SaveTodoResult.Fail }
                    .subscribeOn(Schedulers.io())
            }
        }

}