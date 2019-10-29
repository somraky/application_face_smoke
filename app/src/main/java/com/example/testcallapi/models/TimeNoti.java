package com.example.testcallapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeNoti {
    @SerializedName("n")
    private String n;

    @SerializedName("nModified")
    private int nModified;

    @SerializedName("ok")
    private String ok;

    public String getN() {
        return n;
    }

    public TimeNoti(String n, int nModified, String ok) {
        this.n = n;
        this.nModified = nModified;
        this.ok = ok;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getnModified() {
        return nModified;
    }

    public void setnModified(int nModified) {
        this.nModified = nModified;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
