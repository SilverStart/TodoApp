package com.silverstar.todoapp.mvibase

import io.reactivex.rxjava3.core.Observable

interface MviViewModel<I : MviIntent, S : MviViewState> {

    val states: Observable<S>

    fun processIntents(intents: Observable<I>)
}