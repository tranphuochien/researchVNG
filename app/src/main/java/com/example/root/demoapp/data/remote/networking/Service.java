package com.example.root.demoapp.data.remote.networking;


import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.data.model.FriendListResponse;
import com.example.root.demoapp.data.model.FriendResponse;
import com.facebook.AccessToken;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;
    private static String curPage;
    private static final int LIMIT_ENTRY_REQUEST = 25;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
        this.curPage = "";
    }

    public Observable<ArrayList<Friend>> mFacebookAPI() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        String userId = token.getUserId();
        String tokenString = token.getToken();

        return networkService.getFriendList(userId, tokenString, LIMIT_ENTRY_REQUEST, curPage)
                .observeOn(Schedulers.io())
                .onErrorResumeNext(new Function<Throwable, Observable<? extends FriendListResponse>>() {
                    @Override
                    public Observable<? extends FriendListResponse> apply(@NonNull Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }

                })
                .flatMap(new Function<FriendListResponse, Observable<ArrayList<Friend>>>() {
                    @Override
                    public Observable<ArrayList<Friend>> apply(@NonNull FriendListResponse friendListResponse) throws Exception {
                        //Update afterPage
                        updatePage(friendListResponse);

                        ArrayList<Friend> friendList = new ArrayList<Friend>();

                        for (FriendResponse friend: friendListResponse.getData()) {
                            friendList.add(friend.convertToLocalModel());
                        }
                        return Observable.just(friendList);
                    }
                });
    }

    private void updatePage(FriendListResponse friendListResponse) {
        this.curPage = friendListResponse.getPaging().getCursors().getAfter();
    }
}
