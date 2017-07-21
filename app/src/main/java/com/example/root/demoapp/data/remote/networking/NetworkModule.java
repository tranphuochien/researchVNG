package com.example.root.demoapp.data.remote.networking;


import android.content.Context;
import android.util.Log;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getCacheDir;

@Module
public class NetworkModule {

    @Provides
    Retrofit provideCall(Context context,  File cacheFile) {
        Cache cache = null;
        final Context mContext = context;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request;
                        // Customize the request
                        request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", 4320000))
                                .build();

                        Response response = chain.proceed(request);
                        response.cacheResponse();

                        return response;
                    }
                })*/
                .cache(cache)
                .build();



        return new Retrofit.Builder()
                //baseUrl(/*BuildConfig.BASEURL*/"http://private-b8cf44-androidcleancode.apiary-mock.com/")
                .baseUrl("https://graph.facebook.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
    @Provides
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }

    @Provides
    public File providesCacheFile(){
        Log.d("Debug", "Create Cache");
        return new File(getCacheDir(), "responses");
    }

}
