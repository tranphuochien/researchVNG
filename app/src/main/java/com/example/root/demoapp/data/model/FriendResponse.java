package com.example.root.demoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendResponse {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("picture")
    @Expose
    private PictureAvt picture;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public PictureAvt getPicture() {
        return this.picture;
    }

    public void setPicture(PictureAvt picture) {
        this.picture = picture;
    }

    public Friend convertToLocalModel(){
        return new Friend(this.id, this.name, this.picture.getData().getUrl());
    }

    public class PictureAvt {
        private Data data;

        public void setData(Data data) {this.data = data;}
        public Data getData() { return this.data;}
    }

    public class Data {
        private String is_silhouette;
        private String url;

        public void setIs_silhouette(String is_silhouette) {this.is_silhouette = is_silhouette;}
        public void setUrl(String url) {this.url = url;}
        public String getIs_silhouette() {return this.is_silhouette;}
        public String getUrl() {return this.url;}
    }
}