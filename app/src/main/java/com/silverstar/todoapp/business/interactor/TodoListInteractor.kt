package com.silverstar.todoapp.business.interactor

import com.silverstar.todoapp.data.dao.TodoDao
import com.silverstar.todoapp.mvibase.MviInteractor
import com.silverstar.todoapp.ui.list.TodoListAction
import com.silverstar.todoapp.ui.list.TodoListResult
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

open class TodoListInteractor @Inject constructor(private val todoDao: TodoDao) :
    MviInteractor<TodoListAction, TodoListResult> {
    override val actionProcessor =
        ObservableTransformer<TodoListAction, TodoListResult> { actions ->
            actions.publish {
                it.ofType(TodoListAction.LoadTodoListAction::class.java)
                    .compose(loadTodos)
            }
        }

    private val loadTodos =
        ObservableTransformer<TodoListAction.LoadTodoListAction,
                TodoListResult> { action ->
            action.flatMap {
                todoDao.getAll()
                    .`as`(RxJavaBridge.toV3Observable())
                    .map { TodoListResult.LoadTodoListResult.Success(it) }
                    .cast(TodoListResult::class.java)
                    .subscribeOn(Schedulers.io())
                    .startWith(Observable.just(TodoListResult.IsLoading))
            }
        }

}