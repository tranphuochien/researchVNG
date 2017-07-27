package com.example.root.demoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FriendListResponse {
    @SerializedName("data")
    @Expose
    private List<FriendResponse> data = new ArrayList<FriendResponse>();
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<FriendResponse> getData() {
        return data;
    }

    public void setData(List<FriendResponse> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return this.paging;
    }



}