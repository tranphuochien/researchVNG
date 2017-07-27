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
    @Expose (serialize = false, deserialize = false)
    String next;

    public class Cursors {
        @SerializedName("before")
        @Expose
        String before;
        @SerializedName("after")
        @Expose
        String after;

        public void setBefore(String before) {
            this.before = before;
        }
        public String getBefore () {
            return this.before;
        }
        public void setAfter(String after) {
            this.after = after;
        }
        public String getAfter() {
            return this.after;
        }
    }

    public Cursors getCursors() {return this.cursors;}

    public String getNext() {return this.next;}

    public void setCursors(Cursors cursors) {this.cursors = cursors;}

    public void setNext(String next) { this.next = next;}


}
