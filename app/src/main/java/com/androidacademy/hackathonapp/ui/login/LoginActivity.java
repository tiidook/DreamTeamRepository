package com.androidacademy.hackathonapp.ui.login;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.provider.Settings.Secure;

import com.androidacademy.hackathonapp.R;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter presenter;

    EditText editText;
    Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginButton = findViewById(R.id.login_button);
        editText = findViewById(R.id.login_field);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editText.getText().toString();
                String androidId = Secure.ANDROID_ID;
                presenter.login(login, androidId);

            }
        });
    }
    
    @Override
    public void loginSuccessful() {
        //todo тут открыть экран тасков, закрыть текущий
    }
}
