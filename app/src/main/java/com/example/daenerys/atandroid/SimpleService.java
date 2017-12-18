package com.example.daenerys.atandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by daenerys on 12/16/17.
 */

public interface SimpleService {

    @GET("descricao")
    public List<String> getDescricao();

    @GET("dadosAtividades.php")
    public List<Tarefa> getTarefa();

    @GET("dadosAtividades.php")
    public Call<Example> getExample();
}
