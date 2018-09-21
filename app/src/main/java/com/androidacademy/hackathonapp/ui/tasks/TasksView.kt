package com.androidacademy.hackathonapp.ui.tasks

import com.androidacademy.hackathonapp.data.models.Task
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface TasksView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onLoadedTasks(items: List<Task>)

}