package com.example.root.demoapp.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.root.demoapp.R;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.presentation.di.HasComponent;
import com.example.root.demoapp.presentation.di.components.FriendComponent;
import com.example.root.demoapp.presentation.view.fragments.FriendListFragment;

public class FriendListActivity extends BaseActivity implements HasComponent<FriendComponent>,
        FriendListFragment.FriendListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new FriendListFragment());
        }
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FriendListActivity.class);
    }

    @Override
    public FriendComponent getComponent() {
        return this.getFriendComponent();
    }

    @Override
    public void onFriendClicked(Friend userModel) {
        showToastMessage(String.valueOf(userModel.getName()));
    }
}
