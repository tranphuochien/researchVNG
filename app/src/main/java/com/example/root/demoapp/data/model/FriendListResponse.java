package com.example.root.demoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FriendListResponse {
    @SerializedName("data")
    @Expose
    private List<Friend> data = new ArrayList<Friend>();
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<Friend> getData() {
        return data;
    }

    public void setData(List<Friend> data) {
        this.data = data;
    }



}