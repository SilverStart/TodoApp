package com.silverstar.todoapp.ui.list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.silverstar.todoapp.business.interactor.TodoListInteractor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("TodoListVIewModel 클래스")
internal class TodoListViewModelTest {

    private val mock: TodoListInteractor = mock()

    private lateinit var todoListViewModel: TodoListViewModel

    @Nested
    @DisplayName("값을 내보내지 않는 mock 을 주입했을 때")
    inner class ContextEmptyMock {

        @BeforeEach
        fun prepareMock() {
            whenever(mock.actionProcessor).thenReturn(ObservableTransformer {
                Observable.empty()
            })
        }

        @Nested
        @DisplayName("아무것도 하지 않을 경우")
        inner class ContextDoNothing {
            @Test
            @DisplayName("빈 List 를 가진 State 를 리턴한다")
            fun `it returns the state same with expectedState`() {

                val expectedState = TodoListViewState(false, emptyList())

                todoListViewModel = TodoListViewModel(mock)

                val testObserver = todoListViewModel.states.test()

                testObserver.assertValue(expectedState)
            }
        }
    }
}
