package com.example.daenerys.atandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity  implements View.OnClickListener{

    Button btnSalvar, btnVoltar;
    EditText cpf, nome, email, senha, repSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);


        cpf = findViewById(R.id.editTextCpf);
        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        repSenha = findViewById(R.id.repitaSenha);

        cpf.addTextChangedListener(MaskUtil.insert(cpf, MaskType.CPF));

        btnSalvar = findViewById(R.id.btn_salvar);
        btnVoltar = findViewById(R.id.btn_voltar);

        btnSalvar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);

    }


    public void createAccount(String email, String senha){
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Entre com o enderço de Email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(getApplicationContext(), "Entre com a senha!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (senha.length() < 6) {
            Toast.makeText(getApplicationContext(), "A senha deve possuir no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void voltar(){

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
        public void updateUI(FirebaseUser user){
        if(user!= null){
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
        }
    }
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_salvar:
                if(senha.getText().toString().equals(repSenha.getText().toString())){
                    createAccount(email.getText().toString(), senha.getText().toString());
                }else {
                    Toast.makeText(this,"senhas diferentes",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_voltar:
                voltar();
                break;
        }//switch
    }//onClick
}//class
