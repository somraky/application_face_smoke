package com.example.testcallapi.models;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("_id")
    private String id;

    @SerializedName("UID")
    private int uid;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("DOB")
    private String dob;

    @SerializedName("realname")
    private String realname;

    @SerializedName("gender")
    private String gender;

    @SerializedName("age")
    private String age;

    @SerializedName("social")
    private String social;

    @SerializedName("timestamp")
    private String timestamp;

    public Profile(String id,int uid,String realname,String nickname,String gender,String dob ,String age, String social,String timestamp){
        this.id = id;
        this.uid = uid;
        this.realname = realname;
        this.nickname = nickname;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.social = social;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getRealname() { return realname; }

    public String getNickname() { return nickname; }

    public String getGender() { return gender; }

    public String getDob() { return dob; }

    public String getAge() { return age; }

    public String getSocial() { return social; }

    public String getTimestamp() { return timestamp; }


}
