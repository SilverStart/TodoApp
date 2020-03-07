package com.silverstar.todoapp.mvibase

import io.reactivex.rxjava3.core.ObservableTransformer

interface MviInteractor<A : MviAction, R : MviResult> {

    val actionProcessor: ObservableTransformer<A, R>

}