package com.example.a51newsapp;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApi extends Serializable {
    @GET
    Call<com.example.a51newsapp.NewsModel> getAllNews(@Url String url);
}
