package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.ui.base.BaseViewModel
import com.silverstar.todoapp.util.Event
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction

class TodoInputViewModel :
    BaseViewModel<TodoInputIntent, TodoInputAction, TodoInputResult, TodoInputViewState>() {
    override val reducer: BiFunction<TodoInputViewState, TodoInputResult, TodoInputViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is TodoInputResult.IsLoading -> previousState.copy(isLoading = true)
                is TodoInputResult.SaveTodoResult.Success -> previousState.copy(
                    isLoading = false,
                    isSuccess = Event(
                        true
                    )
                )
                is TodoInputResult.SaveTodoResult.Fail -> previousState.copy(
                    isLoading = false,
                    isSuccess = Event(
                        false
                    )
                )
            }
        }

    override fun actionFromIntent(intent: TodoInputIntent): TodoInputAction {
        return when (intent) {
            is TodoInputIntent.SaveIntent -> {
                TodoInputAction.SaveTodoAction(intent.title, intent.content)
            }
        }
    }

    override val states: Observable<TodoInputViewState> =
        Observable.just(TodoInputViewState(false, Event(true), Event(false)))

    override fun processIntents(intents: Observable<TodoInputIntent>) {
        intents.subscribe(intentsSubject)
    }

}
