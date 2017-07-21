package com.example.root.demoapp.presentation.view.activities;

import android.os.Bundle;

import com.example.root.demoapp.R;
import com.example.root.demoapp.presentation.di.HasComponent;
import com.example.root.demoapp.presentation.di.components.FriendComponent;
import com.example.root.demoapp.presentation.view.fragments.LoginFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<FriendComponent>,
    LoginFragment.LoginListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new LoginFragment());
        }
    }

    @Override
    public FriendComponent getComponent() {
        return this.getFriendComponent();
    }


    @Override
    public void navigation() {
        navigator.navigateToFriendList(this);
    }
}
