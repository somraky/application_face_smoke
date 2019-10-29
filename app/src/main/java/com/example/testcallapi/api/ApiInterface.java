package com.example.testcallapi.api;

import com.example.testcallapi.models.Person;
import com.example.testcallapi.models.Profile;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("getdata/person")
    Call<List<Person>> getPersons();

    @GET("userdata/")
    Call<List<Profile>> getProfile();

    @GET("getperson/{uid}/10")
    Call<List<Profile>> getSpec(
            @Path("uid") Number uid
    );

    @FormUrlEncoded
    @POST("updatetime")
    Call<ResponseBody> sentUpdateTime(
            @Field("_id") String id
    );

}

// picture name date

