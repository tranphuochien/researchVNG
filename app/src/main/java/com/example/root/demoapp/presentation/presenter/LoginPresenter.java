package com.example.root.demoapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.root.demoapp.presentation.view.LoginView;
import com.example.root.demoapp.presentation.view.fragments.LoginFragment;

import javax.inject.Inject;

/**
 * Created by root on 18/07/2017.
 */

public class LoginPresenter implements Presenter{
    @Inject
    LoginPresenter() {
    }

    private LoginView loginView;


    public void setView(@NonNull LoginFragment view) {
        this.loginView = view;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {
        this.loginView = null;
    }


}
