package com.example.root.demoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 20/07/2017.
 */

public class Paging {
    @SerializedName("cursors")
    @Expose
    Cursors cursors;
    @SerializedName("next")
    @Expose
    String next;

    private class Cursors {
        @SerializedName("before")
        @Expose
        String before;
        @SerializedName("after")
        @Expose
        String after;

        public void setBefore(String before) {this.before = before;}
        public String getBefore () {return this.before;}
    }

    public Cursors getCursors() {return this.cursors;}

    public String getNext() {return this.next;}

    public void setCursors(Cursors cursors) {this.cursors = cursors;}

    public void setNext(String next) { this.next = next;}


}
