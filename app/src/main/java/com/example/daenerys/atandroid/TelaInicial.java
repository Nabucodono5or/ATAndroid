package com.example.daenerys.atandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnLogin:
                intent = new Intent(this,TelaLogin.class);
                startActivity(intent);
                break;
            case R.id.btnCadastro:
                intent = new Intent(this,TelaCadastro.class);
                startActivity(intent);
                break;
        }//switch
    }//onClick
}//class
