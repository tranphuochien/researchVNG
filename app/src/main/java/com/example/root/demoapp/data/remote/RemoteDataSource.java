package com.example.root.demoapp.data.remote;

import com.example.root.demoapp.data.DataSource;
import com.example.root.demoapp.data.remote.networking.Service;

import io.reactivex.disposables.Disposable;

/**
 * Created by root on 21/07/2017.
 */

public class RemoteDataSource implements DataSource{
    private Service service;

    public RemoteDataSource(Service service){
        this.service = service;
    }

    public Disposable callFacebookAPI(Service.GetFriendsListCallback callback){
        return service.mFacebookAPI(callback);
    }

    @Override
    public Disposable getData() {
        return null;
    }
}
