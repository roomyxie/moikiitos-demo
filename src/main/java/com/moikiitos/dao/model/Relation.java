package com.moikiitos.dao.model;

public class Relation {
    private Long id;

    private Long followerid;

    private Long followeeid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerid() {
        return followerid;
    }

    public void setFollowerid(Long followerid) {
        this.followerid = followerid;
    }

    public Long getFolloweeid() {
        return followeeid;
    }

    public void setFolloweeid(Long followeeid) {
        this.followeeid = followeeid;
    }
}