package com.example.root.demoapp.data;

import com.example.root.demoapp.data.local.LocalDataSource;
import com.example.root.demoapp.data.remote.RemoteDataSource;
import com.example.root.demoapp.data.remote.networking.Service;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by root on 21/07/2017.
 */

public class Repository implements DataSource{
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;

    @Inject
    public Repository(RemoteDataSource remoteDataSource,
                      LocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public Disposable getDataFromRemote(Service.GetFriendsListCallback callback){
        return ((RemoteDataSource)mRemoteDataSource).callFacebookAPI(callback);
    }

    public Disposable getData() {
        //dummy
        return null;
    }
}
