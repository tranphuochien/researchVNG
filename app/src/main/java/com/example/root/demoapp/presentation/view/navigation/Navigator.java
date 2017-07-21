package com.example.root.demoapp.presentation.view.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.root.demoapp.presentation.view.activities.FriendListActivity;

import javax.inject.Inject;

/**
 * Created by root on 18/07/2017.
 */

public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToFriendList(Context context) {
        if (context != null) {
            Intent intentToLaunch = FriendListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
    /*
    public void navigateToFriendDetails(Context context, int userId) {
        if (context != null) {
            Intent intentToLaunch = FriendDetailsActivity.getCallingIntent(context, userId);
            context.startActivity(intentToLaunch);
        }
    }
    */
}