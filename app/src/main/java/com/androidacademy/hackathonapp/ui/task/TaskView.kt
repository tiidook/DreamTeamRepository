package com.androidacademy.hackathonapp.ui.task

import com.androidacademy.hackathonapp.data.models.Task
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface TaskView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTask(task: Task)

}