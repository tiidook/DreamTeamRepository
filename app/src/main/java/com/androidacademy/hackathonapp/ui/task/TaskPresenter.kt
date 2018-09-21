package com.androidacademy.hackathonapp.ui.task

import com.androidacademy.hackathonapp.data.models.Task
import com.androidacademy.hackathonapp.data.repositories.api.ApiRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import me.codezfox.extension.launchUI

@InjectViewState
class TaskPresenter(

    private val task: Task

) : MvpPresenter<TaskView>() {

    private val apiRepository = ApiRepository()

    fun accept() {
        launchUI {
            apiRepository.acceptTask(task)
        }
    }

    init {
        viewState.showTask(task)
    }

}