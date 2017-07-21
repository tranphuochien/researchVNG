package com.example.root.demoapp.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by root on 21/07/2017.
 */


@Entity
public class FriendORM {
    String id;
    String name;
    String linkAvatar;
    @Generated(hash = 907498698)
    public FriendORM(String id, String name, String linkAvatar) {
        this.id = id;
        this.name = name;
        this.linkAvatar = linkAvatar;
    }
    @Generated(hash = 2019857969)
    public FriendORM() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLinkAvatar() {
        return this.linkAvatar;
    }
    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }
}
