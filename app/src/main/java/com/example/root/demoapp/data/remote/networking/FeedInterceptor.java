package com.example.root.demoapp.data.remote.networking;

import android.content.Context;

import com.example.root.demoapp.utils.NetworkChangeReceiver;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by root on 27/07/2017.
 */

public class FeedInterceptor {
    private final static String TAG = FeedInterceptor.class.getSimpleName();

    /**
     * Validate cache, return stream. Return cache if no network.
     * @param context
     * @return
     */
    public static Interceptor getOnlineInterceptor(final Context context){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                String cacheControl = originalResponse.header("Cache-Control");
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build();
                } else {
                    return originalResponse;
                }
            }
        };

        return interceptor;
    }

    /**
     * Get me cache.
     * @param context
     * @return
     */
    public static Interceptor getOfflineInterceptor(final Context context){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkChangeReceiver.isNetworkAvailable(context)) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached")
                            .build();
                }
                return chain.proceed(request);
            }
        };

        return interceptor;
    }
}
