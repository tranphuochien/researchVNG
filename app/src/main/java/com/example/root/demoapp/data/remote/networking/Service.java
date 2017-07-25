package com.example.root.demoapp.data.remote.networking;


import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.data.model.FriendListResponse;
import com.example.root.demoapp.data.model.FriendResponse;
import com.facebook.AccessToken;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Observable<ArrayList<Friend>> mFacebookAPI() {
        AccessToken token = AccessToken.getCurrentAccessToken();

        return networkService.getFriendList(token.getUserId(), token.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, Observable<? extends FriendListResponse>>() {
                    @Override
                    public Observable<? extends FriendListResponse> apply(@NonNull Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }

                })
                .flatMap(new Function<FriendListResponse, Observable<ArrayList<Friend>>>() {
                    @Override
                    public Observable<ArrayList<Friend>> apply(@NonNull FriendListResponse friendListResponse) throws Exception {
                        ArrayList<Friend> friendList = new ArrayList<Friend>();

                        for (FriendResponse friend: friendListResponse.getData()) {
                            friendList.add(friend.convertToLocalModel());
                        }
                        return Observable.just(friendList);
                    }
                });
    }


}
