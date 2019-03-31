package com.lab.mymvp.business.net;

import com.lab.mymvp.base.entity.ItemData;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface Rest {

    public static String API_ALL_ITEMS = "/photos";
    public static String CONTENT_TYPE = "Content-Type: application/json";

    @Headers(CONTENT_TYPE)
    @GET(API_ALL_ITEMS)
    void allItems(Callback<List<ItemData>> callback);
}
