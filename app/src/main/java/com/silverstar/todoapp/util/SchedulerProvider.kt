package com.silverstar.todoapp.util

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}