package com.example.root.demoapp.data.remote.networking;

import com.example.root.demoapp.data.model.FriendListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NetworkService {
    @GET("{user_id}/taggable_friends")
    Observable<FriendListResponse> getFriendList(
            @Path("user_id") String userId,
            @Query("access_token") String access_token
            );

}
