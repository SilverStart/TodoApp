package com.silverstar.todoapp.ui.input

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.silverstar.todoapp.mvibase.MviInteractor
import com.silverstar.todoapp.util.Event
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("TodoInputViewModel 클래스")
internal class TodoInputViewModelTest {

    private val mock: MviInteractor<TodoInputAction, TodoInputResult> = mock()

    private lateinit var viewModel: TodoInputViewModel

    private fun subject(
        interactor: MviInteractor<TodoInputAction, TodoInputResult>,
        title: String,
        content: String
    ): TestObserver<TodoInputViewState> {
        viewModel = TodoInputViewModel(interactor)

        val test = viewModel.states.test()

        viewModel.processIntents(
            Observable.just(
                TodoInputIntent.SaveIntent(
                    title,
                    content
                )
            )
        )

        return test
    }

    @Nested
    @DisplayName("타이틀 문자열이 비어있을 때")
    inner class ContextWithEmptyTitle {
        private val emptyTitle = ""

        @Nested
        @DisplayName("processIntents() 에 SaveIntent 를 전달할 경우")
        inner class ContextCallsProcessIntentsWithSaveIntent {

            @BeforeEach
            fun prepareMock() {
                whenever(mock.actionProcessor).thenReturn(ObservableTransformer {
                    Observable.just(TodoInputResult.SaveTodoResult.Fail(TodoInputError.EMPTY_TITLE))
                })
            }

            @Test
            @DisplayName("TodoInputError 가 EMPTY_TITLE 인 State 를 리턴한다")
            fun it_returns_a_state_that_isEmptyTitle_is_true() {

                val content = "content"
                val expectedState =
                    TodoInputViewState(false, Event(false), Event(TodoInputError.EMPTY_TITLE))

                val test = subject(mock, emptyTitle, content)

                test.assertValueAt(1, expectedState)
            }
        }
    }

    @Nested
    @DisplayName("타이틀 문자열이 비어있지 않을 때")
    inner class ContextWithTitleWhichHasValue {
        private val titleThatHasValue = "a title text"

        @Nested
        @DisplayName("processIntents() 에 SaveIntent 를 전달할 경우")
        inner class ContextCallsProcessIntentsWithSaveIntent {

            @BeforeEach
            fun prepareMock() {
                whenever(mock.actionProcessor).thenReturn(ObservableTransformer {
                    Observable.just(TodoInputResult.SaveTodoResult.Success)
                })
            }

            @Test
            @DisplayName("isSuccess 가 true 인 State 를 리턴한다")
            fun it_returns_a_state_that_isEmptyTitle_is_true() {

                val content = "content"
                val expectedState =
                    TodoInputViewState(false, Event(true), Event(TodoInputError.NONE))

                val test = subject(mock, titleThatHasValue, content)

                test.assertValueAt(1, expectedState)
            }
        }
    }
}