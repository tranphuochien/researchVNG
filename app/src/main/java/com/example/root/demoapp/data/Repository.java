package com.example.root.demoapp.data;

import com.example.root.demoapp.data.local.LocalDataSource;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.data.remote.RemoteDataSource;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by hientp on 21/07/2017.
 */

public class Repository implements DataSource{
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    private Observable<ArrayList<Friend>> mObservableLocal;
    private Observable<ArrayList<Friend>> mObservableRemote;

    @Inject
    public Repository(RemoteDataSource remoteDataSource,
                      LocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public Observable<ArrayList<Friend>> getData() {
        this.mObservableLocal = mLocalDataSource.getData();
        this.mObservableRemote = mRemoteDataSource.getData();

        return Observable
                .concat(this.mObservableLocal, mObservableRemote)
                .filter(new Predicate<ArrayList<Friend>>() {
                    @Override
                    public boolean test(@NonNull ArrayList<Friend> friends) throws Exception {
                        return friends.size() > 0;
                    }
                })
                .take(1);
    }
}
