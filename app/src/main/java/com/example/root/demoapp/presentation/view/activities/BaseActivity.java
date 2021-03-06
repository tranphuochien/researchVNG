package com.example.root.demoapp.presentation.view.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.root.demoapp.MyApplication;
import com.example.root.demoapp.data.remote.networking.NetworkModule;
import com.example.root.demoapp.presentation.di.HasComponent;
import com.example.root.demoapp.presentation.di.components.ApplicationComponent;
import com.example.root.demoapp.presentation.di.components.DaggerFriendComponent;
import com.example.root.demoapp.presentation.di.components.FriendComponent;
import com.example.root.demoapp.presentation.di.modules.ActivityModule;
import com.example.root.demoapp.presentation.view.navigation.Navigator;
import com.example.root.demoapp.utils.MessageEvent;
import com.example.root.demoapp.utils.NetworkChangeReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * Created by root on 18/07/2017.
 */

public abstract class BaseActivity extends Activity implements HasComponent<FriendComponent> {

    @Inject
    Navigator navigator;
    private BroadcastReceiver mReceiver;
    private FriendComponent friendComponent;
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getApplicationComponent().inject(this);
        initializeInjector();

        super.onCreate(savedInstanceState);

        //Register network change receiver
        eventBus = ((MyApplication)getApplication()).getEventBus();
        mReceiver = new NetworkChangeReceiver();
        registerReceiver(mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    }

    protected void refreshComponent(){
        initializeInjector();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Register evenbus
        this.eventBus.register(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }


    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected NetworkModule getNetworkModule() {
        return new NetworkModule ();
    }

    protected FriendComponent getFriendComponent() {
        return friendComponent;
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    private void initializeInjector() {
        this.friendComponent = DaggerFriendComponent.builder()
                .applicationComponent(getApplicationComponent())
                .networkModule(getNetworkModule())
                .activityModule(getActivityModule())
                .build();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        //Handle event
        showToastMessage(messageEvent.getmMessage());

        //Remove current sticky event
        eventBus.removeStickyEvent(MessageEvent.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        mReceiver = null;
    }
}
