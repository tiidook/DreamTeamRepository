package com.androidacademy.hackathonapp.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loginSuccessful() {
        //todo тут открыть экран тасков, закрыть текущий
    }
}
