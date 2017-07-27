/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demoapp.presentation.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.demoapp.R;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.presentation.di.components.FriendComponent;
import com.example.root.demoapp.presentation.presenter.FriendListPresenter;
import com.example.root.demoapp.presentation.view.FriendListView;
import com.example.root.demoapp.presentation.view.adapter.EndlessRecyclerViewScrollListener;
import com.example.root.demoapp.presentation.view.adapter.FriendsAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows a list of Users.
 */
public class FriendListFragment extends BaseFragment implements FriendListView {
    public interface FriendListListener {
        void onFriendClicked(final Friend userModel);
    }

    @Inject
    FriendListPresenter friendListPresenter;
    @Inject
    FriendsAdapter friendsAdapter;

    @Bind(R.id.rv_users) RecyclerView rv_users;

    private FriendListListener friendListListener;

    public FriendListFragment() {
        setRetainInstance(true);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FriendListListener) {
            this.friendListListener = (FriendListListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(FriendComponent.class).inject(this);
        this.friendListPresenter.setRepository(
                this.getComponent(FriendComponent.class).getRepository());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.friendListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.friendListPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.friendListPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.friendListPresenter.destroy();
    }

    @Override public void onDetach() {
        super.onDetach();
        this.friendListListener = null;
    }


    @Override public void renderUserList(Collection<Friend> userModelCollection) {
        if (userModelCollection != null) {
            this.friendsAdapter.setUsersCollection(userModelCollection);
        }
    }

    @Override public void viewUser(Friend model) {
        if (this.friendListListener != null) {
            this.friendListListener.onFriendClicked(model);
        }
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.friendsAdapter.setOnItemClickListener(
                new FriendsAdapter.OnItemClickListener() {
                    @Override
                    public void onUserItemClicked(Friend friendResponse) {
                        if (FriendListFragment.this.friendListPresenter != null && friendResponse != null) {
                            FriendListFragment.this.friendListPresenter.onFriendItemClicked(friendResponse);
                        }
                    }
                });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context());
        this.rv_users.setLayoutManager(linearLayoutManager);
        this.rv_users.setAdapter(friendsAdapter);
        this.rv_users.addOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                        loadNextDataFromApi(page);
                        Log.d("Debug", "On load more");

                    }
                }
        );
    }

    private void loadNextDataFromApi(int page) {
        /*ArrayList<Friend> list = new ArrayList<>();
        list.add(new Friend("AaIziyG1JEkBZEuLPbIpL_RKb7yvtCViFtp7wxXQYh_cCVu4z_By2eM5wEGyPk4W_M_E4KUhRLKfaRmWDwd0IjIXsYNMu5AOH5CHtjnb4wNerQ","abc", "https://scontent.xx.fbcdn.net/v/t1.0-1/c8.0.50.50/p50x50/11889657_528231363995171_106694820207113550_n.jpg?oh=68088b2d90424c8d0e745f1b4bab5bad&oe=5A0694D2"));

        this.friendsAdapter.setUsersCollection(list);
        */
        this.friendListPresenter.loadMore();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.friendListPresenter.initialize();
    }

}
