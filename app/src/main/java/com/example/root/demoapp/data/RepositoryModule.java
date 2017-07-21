package com.example.root.demoapp.data;

import com.example.root.demoapp.data.local.LocalDataSource;
import com.example.root.demoapp.data.remote.RemoteDataSource;
import com.example.root.demoapp.data.remote.networking.Service;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 21/07/2017.
 */

@Module
public class RepositoryModule {

    @Provides
    LocalDataSource provideLocalDataSource() {
        return new LocalDataSource();
    }

    @Provides
    RemoteDataSource provideRemoteDataSource(Service service) {
        return new RemoteDataSource(service);
    }
}
