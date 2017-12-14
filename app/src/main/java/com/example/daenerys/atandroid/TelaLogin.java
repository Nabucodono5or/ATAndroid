package com.example.daenerys.atandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity implements View.OnClickListener{

    Button btnEntrar, btnVoltar;
    EditText email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        email = findViewById(R.id.editTextEmail2);
        senha = findViewById(R.id.editTextSenha2);

        btnEntrar = findViewById(R.id.buttonEntrar);
        btnVoltar = findViewById(R.id.buttonVoltar);

    }


    public void signIn(String email, String password){
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Entre com o enderço de Email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Entre com a senha!", Toast.LENGTH_SHORT).show();
            return;
        }
    }//signIn


    public void voltar(){

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonEntrar:
                signIn(email.getText().toString(), senha.getText().toString());
                break;
            case R.id.buttonVoltar:
                voltar();
                break;
        }
    }
}//class
