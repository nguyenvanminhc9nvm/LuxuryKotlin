package com.example.luxurykotlin.utils.scheduler

import io.reactivex.Scheduler

/**
 * Created by OpenYourEyes on icon_navigation_store/4/2020
 */
interface SchedulerProvider {
    val computation: Scheduler
    val io: Scheduler
    val ui: Scheduler
}