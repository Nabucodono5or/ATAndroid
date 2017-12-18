package com.example.daenerys.atandroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daenerys on 12/17/17.
 */

public class RestClient {
    static final String BASE_URL = "http://infnet.educacao.ws/";

    private static SimpleService simpleService;

    public static SimpleService getInstance(){
        if(simpleService == null){
            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            simpleService = retrofit.create(SimpleService.class);
        }
        return simpleService;
    }
}
