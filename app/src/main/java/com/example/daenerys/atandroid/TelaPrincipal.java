package com.example.daenerys.atandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipal extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        listView = (ListView) findViewById(R.id.list_view);

        SegundaRest segundaRest = new SegundaRest();

        segundaRest.start().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(retrofit2.Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    Example ex = response.body();
                    TarefaAdapter adapter = new TarefaAdapter(TelaPrincipal.this,ex.getTarefa());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Example> call, Throwable t) {

            }
        });

    }// onCreate
}//class
