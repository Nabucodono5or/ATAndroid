package com.example.daenerys.atandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        listView = (ListView) findViewById(R.id.list_view);

        new AsyncTask<Void, Void, List<Tarefa>>() {
            @Override
            protected List<Tarefa> doInBackground(Void... voids) {
                List<Tarefa> list = RestClient.getInstance().getTarefa();
                return list;
            }

            @Override
            protected void onPostExecute(List<Tarefa> tarefas) {
                TarefaAdapter adapter = new TarefaAdapter(TelaPrincipal.this,tarefas);
                listView.setAdapter(adapter);
            }
        }.execute();
    }// onCreate
}//class
