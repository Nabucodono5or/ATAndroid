package com.example.daenerys.atandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastro extends AppCompatActivity  implements View.OnClickListener{

    private FirebaseAuth mAuth;
    Button btnSalvar, btnVoltar;
    EditText cpf, nome, email, senha, repSenha;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);


        mAuth = FirebaseAuth.getInstance();

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

        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    //por enquanto vai ficar sem database
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    String userId = ref.push().getKey();

                    Usuario usuario = new Usuario(nome.getText().toString(), cpf.getText().toString());
                    ref.child(user.getUid()).setValue(usuario);

                    updateUI(user);

                }else {
                    Log.w(TAG,"error:UserWithEmailAndPassword");
                }
            }
        });

    }

    public void voltar(){
        Intent intent = new Intent(this, TelaInicial.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void updateUI(FirebaseUser user){
        if(user!= null){
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
        }
    }


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
