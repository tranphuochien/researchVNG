/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.root.demoapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.root.demoapp.data.Repository;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.data.model.FriendListResponse;
import com.example.root.demoapp.data.remote.networking.NetworkError;
import com.example.root.demoapp.data.remote.networking.Service;
import com.example.root.demoapp.presentation.view.FriendListView;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class FriendListPresenter implements Presenter {
    private Repository repository;
    private FriendListView viewFriendsList;
    private CompositeDisposable disposable;

    @Inject
    public FriendListPresenter() {
        disposable = new CompositeDisposable();
    }

    public void setView(@NonNull FriendListView view) {
        this.viewFriendsList = view;
    }

    public void setRepository(@NonNull Repository repository) {
        this.repository = repository;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {
        this.viewFriendsList = null;
        if (disposable.isDisposed())
            disposable.dispose();
    }

    public void initialize() {
        this.loadFriendList();
    }

    /**
     * Loads all friends.
     */
    private void loadFriendList() {
        final ArrayList<Friend> friendList = new ArrayList<>();

        Disposable subscription = repository.getDataFromRemote(
                new Service.GetFriendsListCallback() {
                    @Override
                    public void onSuccess(FriendListResponse cityListResponse) {
                        for (Friend friend: cityListResponse.getData()) {
                            friendList.add(friend);
                        }

                        showFriendsCollectionInView(friendList);
                    }

                    @Override
                    public void onError(NetworkError networkError) {

                    }
                });

        disposable.add(subscription);
    }

    private void showFriendsCollectionInView(Collection<Friend> friendsCollection) {
        this.viewFriendsList.renderUserList(friendsCollection);
    }


    public void onFriendItemClicked(Friend friend) {
        this.viewFriendsList.viewUser(friend);
    }

}
