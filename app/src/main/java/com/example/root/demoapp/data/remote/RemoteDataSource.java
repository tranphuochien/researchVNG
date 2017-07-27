package com.example.root.demoapp.data.remote;

import com.example.root.demoapp.data.DataSource;
import com.example.root.demoapp.data.model.Friend;
import com.example.root.demoapp.data.remote.networking.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 21/07/2017.
 */

public class RemoteDataSource implements DataSource{
    private Service service;


    @Inject
    public RemoteDataSource(Service service){
        this.service = service;
    }

    @Override
    public Observable<ArrayList<Friend>> getData() {
        return service.mFacebookAPI();
    }
}
