package com.androidacademy.hackathonapp.ui.tasks

import com.androidacademy.hackathonapp.data.repositories.api.ApiRepository
import com.androidacademy.hackathonapp.ui.task.TaskView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import me.codezfox.errors.showMessage
import me.codezfox.extension.awaitFold
import me.codezfox.extension.launchUI

@InjectViewState
class TasksPresenter : MvpPresenter<TaskView>() {

    private val apiRepository = ApiRepository()

    init {
        loadTasks()
    }

    fun loadTasks() {
        launchUI {
            apiRepository.loadTasks().awaitFold({
                viewState.onLoadedTasks(it)
            }, {
                viewState.showMessage(it)
            })
        }
    }

}