package com.silverstar.todoapp.business.interactor

import com.nhaarman.mockitokotlin2.mock
import com.silverstar.todoapp.data.dao.TodoDao
import com.silverstar.todoapp.ui.input.TodoInputAction
import com.silverstar.todoapp.ui.input.TodoInputError
import com.silverstar.todoapp.ui.input.TodoInputResult
import com.silverstar.todoapp.util.TestSchedulerProvider
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("TodoInputInteractor 클래스")
class TodoInputInteractorTest {

    private val schedulerProvider = TestSchedulerProvider()

    private val dao: TodoDao = mock()

    private lateinit var interactor: TodoInputInteractor

    @Nested
    @DisplayName("타이틀 텍스트가 비어있을 때")
    inner class ContextWithEmptyTitle {
        val emptyTitle = ""

        @Nested
        @DisplayName("SaveTodoAction 을 보낼 경우")
        inner class ContextWithSaveTodoAction {

            @Test
            @DisplayName("SaveTodoResult.Fail(TodoInputError.EMPTY_TITLE) 을 리턴한다")
            fun itReturnsTheFailedResultWithEmptyTitleError() {
                val content = "content text"

                interactor = TodoInputInteractor(dao, schedulerProvider)

                Observable.just(
                    TodoInputAction.SaveTodoAction(emptyTitle, content)
                )
                    .compose(interactor.actionProcessor)
                    .test()
                    .assertValue(TodoInputResult.SaveTodoResult.Fail(TodoInputError.EMPTY_TITLE))
            }
        }
    }

    @Nested
    @DisplayName("타이틀 텍스트가 비어있지 않을 때")
    inner class ContextWithTitleThatHasValue {
        val titleThatHasValue = "title text!!!"

        @Nested
        @DisplayName("SaveTodoAction 을 보낼 경우")
        inner class ContextWithSaveTodoAction {

            @Test
            @DisplayName("SaveTodoResult.Success 를 리턴한다")
            fun itReturnsTheFailedResultWithEmptyTitleError() {
                val content = "content text"

                interactor = TodoInputInteractor(dao, schedulerProvider)

                Observable.just(
                    TodoInputAction.SaveTodoAction(titleThatHasValue, content)
                )
                    .compose(interactor.actionProcessor)
                    .test()
                    .assertValueAt(1, TodoInputResult.SaveTodoResult.Success)
            }
        }
    }

}