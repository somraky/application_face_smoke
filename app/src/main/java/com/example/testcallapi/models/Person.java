package com.example.testcallapi.models;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("_id")
    private String id;

    @SerializedName("UID")
    private int uid;

    @SerializedName("description")
    private String desc;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("realname")
    private String realname;

    public Person(String id, int uid, String desc, String timestamp, String realname) {
        this.id = id;
        this.uid = uid;
        this.desc = desc;
        this.timestamp = timestamp;
        this.realname = realname;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRealname() { return realname; }
}
