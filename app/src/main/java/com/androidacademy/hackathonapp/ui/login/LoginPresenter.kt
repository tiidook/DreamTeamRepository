package com.androidacademy.hackathonapp.ui.login

import com.androidacademy.hackathonapp.data.repositories.api.ApiRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import me.codezfox.errors.showMessage
import me.codezfox.extension.awaitFold
import me.codezfox.extension.launchUI

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    private val apiRepository = ApiRepository()

    fun login(nickname: String, deviceId: String) {
        launchUI {
            apiRepository.login(nickname, deviceId).awaitFold({
                viewState.loginSuccessful()
            }, {
                viewState.showMessage(it)
            })
        }
    }

}