package com.example.root.demoapp.presentation.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.root.demoapp.R;
import com.example.root.demoapp.presentation.di.components.FriendComponent;
import com.example.root.demoapp.presentation.presenter.LoginPresenter;
import com.example.root.demoapp.presentation.view.LoginView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 18/07/2017.
 */

public class LoginFragment extends BaseFragment implements LoginView {

    @Override
    public void checkLogin() {
        AccessToken token = AccessToken.getCurrentAccessToken();

        if (token != null) {
            Log.d("Debug", token.getUserId());
            Log.d("Debug", token.getToken());
            //loginListener.navigation();
        }
    }

    public interface LoginListener {
        void navigation();
    }

    private LoginListener loginListener;
    private CallbackManager callbackManager;

    @Bind(R.id.btnShow)
    Button btnShow;

    @Bind(R.id.login_button)
    LoginButton btnLogin;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LoginListener) {
            loginListener = (LoginListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginListener = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(FriendComponent.class).inject(this);
        loginPresenter.setView(this);
        checkLogin();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);

        callbackManager = CallbackManager.Factory.create();
        btnLogin.setFragment(this);
        btnLogin.setReadPermissions("email", "user_friends", "public_profile");


        // Callback registration
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginListener.navigation();
                Log.d("Debug", "success");
            }

            @Override
            public void onCancel() {
                Log.d("Debug", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("Debug", "onError");
            }
        });
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loginPresenter.setView(this);
    }

    @OnClick(R.id.btnShow)
    void navigateToList(){
        loginListener.navigation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode, data);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.loginPresenter.destroy();
    }
}
