package com.example.daenerys.atandroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daenerys on 12/18/17.
 */

public class SegundaRest {
    static final String BASE_URL = "http://infnet.educacao.ws/";

    private static SimpleService simpleService;

    public Call<Example> start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        simpleService = retrofit.create(SimpleService.class);

        Call<Example> call = simpleService.getExample();
        return call;
    }

}
