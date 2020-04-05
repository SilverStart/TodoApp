package com.silverstar.todoapp.ui.input

import com.silverstar.todoapp.util.Event
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("TodoInputViewModel 클래스")
internal class TodoInputViewModelTest {

    private lateinit var viewModel: TodoInputViewModel

    @Nested
    @DisplayName("타이틀 문자열이 비어있을 때")
    inner class ContextWithEmptyTitle {
        private val emptyTitle = ""

        @Nested
        @DisplayName("processIntents() 에 SaveIntent 를 전달할 경우")
        inner class ContextCallsProcessIntentsWithSaveIntent {

            @Test
            @DisplayName("isEmptyTitle 이 true 인 State 를 리턴한다")
            fun it_returns_a_state_that_isEmptyTitle_is_true() {

                val content = "content"
                val expectedState = TodoInputViewState(false, Event(true), Event(false))

                viewModel = TodoInputViewModel()

                val test = viewModel.states.test()

                viewModel.processIntents(
                    Observable.just(
                        TodoInputIntent.SaveIntent(
                            emptyTitle,
                            content
                        )
                    )
                )

                test.assertValue(expectedState)
            }
        }
    }
}