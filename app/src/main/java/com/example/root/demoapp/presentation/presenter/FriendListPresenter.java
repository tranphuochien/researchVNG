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
import android.util.Log;

import com.example.root.demoapp.data.Repository;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.presentation.view.FriendListView;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


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
        Log.d("Debug", "Dispose subcribe");
        /*if (disposable.isDisposed()) {
            disposable.dispose();
        }*/
       disposable.clear();
    }

    public void initialize() {
        this.loadFriendList();
    }

    public void loadMore() {
        this.loadFriendList();
    }

    /**
     * Loads all friends.
     */
    private void loadFriendList() {
        Disposable subscription  =  repository.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<Friend>>() {

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ArrayList<Friend> friends) {
                        showFriendsCollectionInView(friends);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        disposable.add(subscription);
    }

    private void showFriendsCollectionInView(Collection<Friend> friendsCollection) {
        this.viewFriendsList.renderUserList(friendsCollection);
    }


    public void onFriendItemClicked(Friend model) {
        this.viewFriendsList.viewUser(model);
    }

}
