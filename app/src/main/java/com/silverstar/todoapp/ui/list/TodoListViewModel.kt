package com.silverstar.todoapp.ui.list

import com.silverstar.todoapp.business.interactor.TodoListInteractor
import com.silverstar.todoapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import javax.inject.Inject

class TodoListViewModel @Inject constructor(todoListInteractor: TodoListInteractor) :
    BaseViewModel<TodoListIntent, TodoListAction, TodoListResult, TodoListViewState>() {
    override val reducer: BiFunction<TodoListViewState, TodoListResult, TodoListViewState> =
        BiFunction { latestState, result ->
            when (result) {
                is TodoListResult.IsLoading -> {
                    latestState.copy(isLoading = true, todoList = emptyList())
                }
                is TodoListResult.Success -> {
                    latestState.copy(isLoading = false, todoList = result.todoList)
                }
            }
        }

    override fun actionFromIntent(intent: TodoListIntent): TodoListAction {
        when (intent) {
            TodoListIntent.LoadTodoListIntent -> {
                return TodoListAction.LoadTodoListAction
            }
        }
    }

    override val states: Observable<TodoListViewState> =
        intentsSubject.map(::actionFromIntent)
            .compose(todoListInteractor.actionProcessor)
            .scan(TodoListViewState.idle(), reducer)

    override fun processIntents(intents: Observable<TodoListIntent>) {
        intents.subscribe(intentsSubject)
    }

}
