package com.silverstar.todoapp.ui.base

import androidx.lifecycle.ViewModel
import com.silverstar.todoapp.mvibase.MviAction
import com.silverstar.todoapp.mvibase.MviIntent
import com.silverstar.todoapp.mvibase.MviResult
import com.silverstar.todoapp.mvibase.MviViewModel
import com.silverstar.todoapp.mvibase.MviViewState
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel<I : MviIntent, A : MviAction, R : MviResult, S : MviViewState> :
    ViewModel(),
    MviViewModel<I, S> {

    protected abstract val reducer: BiFunction<S, R, S>

    protected val intentsSubject: PublishSubject<I> = PublishSubject.create()

    protected abstract fun actionFromIntent(intent: I): A
}