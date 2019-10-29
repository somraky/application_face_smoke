package com.example.testcallapi.api;

import com.example.testcallapi.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface2 {
    @GET("/getrecent/second")
    Call<List<Person>> getPersons();

    @GET("/getrecent/first")
    Call<List<Person>> getfirstPersons();
}