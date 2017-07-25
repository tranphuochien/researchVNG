package com.example.root.demoapp.data;

import com.example.root.demoapp.data.model.Friend;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by root on 21/07/2017.
 */

public interface DataSource {
    Observable<ArrayList<Friend>> getData();

    public interface GetDataCallback {
        void onSuccess(ArrayList<Friend> friendList);

        void onError();
    }
}
