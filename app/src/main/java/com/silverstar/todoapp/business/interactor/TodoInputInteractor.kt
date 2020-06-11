package com.silverstar.todoapp.business.interactor

import com.silverstar.todoapp.business.dao.TodoDao
import com.silverstar.todoapp.business.entity.Todo
import com.silverstar.todoapp.mvibase.MviInteractor
import com.silverstar.todoapp.ui.input.TodoInputAction
import com.silverstar.todoapp.ui.input.TodoInputError
import com.silverstar.todoapp.ui.input.TodoInputResult
import com.silverstar.todoapp.util.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

open class TodoInputInteractor @Inject constructor(
    private val todoDao: TodoDao,
    private val schedulerProvider: SchedulerProvider
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
                if (it.title.isEmpty()) returnEmptyTitleResult()
                else returnSaveBehaviorResult(it)
            }
        }

    private fun returnEmptyTitleResult(): Observable<TodoInputResult> =
        Observable.just(TodoInputResult.SaveTodoResult.Fail(TodoInputError.EMPTY_TITLE))

    private fun returnSaveBehaviorResult(action: TodoInputAction.SaveTodoAction): Observable<TodoInputResult> =
        Observable
            .fromCallable { todoDao.insert(Todo(action.title, action.content, false)) }
            .map { TodoInputResult.SaveTodoResult.Success }
            .cast(TodoInputResult::class.java)
            .startWith(Observable.just(TodoInputResult.IsLoading))
            .onErrorReturn { TodoInputResult.SaveTodoResult.Fail(TodoInputError.FAILED_TO_SAVE) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
}