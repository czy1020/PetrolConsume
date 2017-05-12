package com.example.czy.petrolconsume.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CZY on 17/4/24.
 */

public class HttpManager {

    private Retrofit retrofit;
    private BearApi bearApi;
    private static class SingleHolder{
        private static final HttpManager INSTANCE = new HttpManager();
    }
    public static HttpManager getInstance(){
        return SingleHolder.INSTANCE;
    }
    private HttpManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BearApi.BEAR_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bearApi = retrofit.create(BearApi.class);
    }
    public BearApi getBearApi(){
        return bearApi;
    }
}
