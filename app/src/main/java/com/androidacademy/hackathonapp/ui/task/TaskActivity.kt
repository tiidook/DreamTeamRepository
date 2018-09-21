package com.androidacademy.hackathonapp.ui.task

import android.os.Bundle
import com.androidacademy.hackathonapp.R
import com.androidacademy.hackathonapp.data.models.Task
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_task.*
import me.codezfox.extension.onClick

class TaskActivity : MvpAppCompatActivity(), TaskView {

    @InjectPresenter
    lateinit var presenter: TaskPresenter

    @ProvidePresenter
    fun providePresenter() = TaskPresenter(Task("Title", "username", "description", "status", 5664))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        btnOk.onClick {
            presenter.accept()
        }
    }

    override fun showTask(task: Task) {
        textVewTitle.text = task.title
        description.text = task.description
        cost.text = task.cost.toString()
        username.text = task.username
    }

}