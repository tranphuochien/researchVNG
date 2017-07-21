package com.example.root.demoapp.data.remote.networking;


import android.util.Log;

import com.example.root.demoapp.data.model.FriendListResponse;
import com.facebook.AccessToken;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Disposable mFacebookAPI(final GetFriendsListCallback callback) {
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
                .subscribeWith(new DisposableObserver<FriendListResponse>() {
                    @Override
                    public void onNext(@NonNull FriendListResponse friendListResponse) {
                        callback.onSuccess(friendListResponse);
                        Log.d("Debug", "Request onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(new NetworkError(e));
                        Log.d("Debug", "Request onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface GetFriendsListCallback {
        void onSuccess(FriendListResponse cityListResponse);

        void onError(NetworkError networkError);
    }
}
