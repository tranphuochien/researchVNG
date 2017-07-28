package com.example.root.demoapp.data.local;

import com.example.root.demoapp.data.DataSource;
import com.example.root.demoapp.data.model.DaoMaster;
import com.example.root.demoapp.data.model.DaoSession;
import com.example.root.demoapp.data.model.Friend;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by root on 21/07/2017.
 */

public class LocalDataSource implements DataSource {
    private final DaoSession mDaoSession;

    @Inject
    public LocalDataSource (DbOpenHelper dbOpenHelper) {
        this.mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();

    }
    @Override
    public Observable<ArrayList<Friend>> getData() {
        final ArrayList<Friend> list = new ArrayList<>();
        //list.add(new Friend("AaIziyG1JEkBZEuLPbIpL_RKb7yvtCViFtp7wxXQYh_cCVu4z_By2eM5wEGyPk4W_M_E4KUhRLKfaRmWDwd0IjIXsYNMu5AOH5CHtjnb4wNerQ","abc", "https://scontent.xx.fbcdn.net/v/t1.0-1/c8.0.50.50/p50x50/11889657_528231363995171_106694820207113550_n.jpg?oh=68088b2d90424c8d0e745f1b4bab5bad&oe=5A0694D2"));

        return Observable.defer(new Callable<ObservableSource<? extends ArrayList<Friend>>>() {
            @Override
            public ObservableSource<? extends ArrayList<Friend>> call() throws Exception {
                return Observable.just(list);
            }
        });
    }

    public DaoSession getmDaoSession() {
        return this.mDaoSession;
    }
}
