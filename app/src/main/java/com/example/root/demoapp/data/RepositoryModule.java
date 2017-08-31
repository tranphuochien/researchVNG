package com.example.root.demoapp.data;

import android.content.Context;

import com.example.root.demoapp.data.local.DbOpenHelper;
import com.example.root.demoapp.data.local.LocalDataSource;
import com.example.root.demoapp.data.remote.RemoteDataSource;
import com.example.root.demoapp.data.remote.networking.Service;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hientp on 21/07/2017.
 */

@Module
public class RepositoryModule {

    @Provides
    LocalDataSource provideLocalDataSource(DbOpenHelper dbOpenHelper) {
        return new LocalDataSource(dbOpenHelper);
    }

    @Provides
    RemoteDataSource provideRemoteDataSource(Service service) {
        return new RemoteDataSource(service);
    }

    @Provides
    DbOpenHelper provideDbOpenHelper(Context context){
        return new DbOpenHelper(context);
    }
}
