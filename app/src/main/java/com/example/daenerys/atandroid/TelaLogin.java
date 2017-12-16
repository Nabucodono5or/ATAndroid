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

public class TelaLogin extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    Button btnEntrar, btnVoltar;
    EditText email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editTextEmail2);
        senha = findViewById(R.id.editTextSenha2);

        btnEntrar = findViewById(R.id.buttonEntrar);
        btnVoltar = findViewById(R.id.buttonVoltar);

        btnEntrar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
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

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(TelaLogin.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }//signIn


    public void updateUI(FirebaseUser user){
        if(user!= null){
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
        }
    }


    public void voltar(){
        Intent intent = new Intent(this, TelaInicial.class);
        startActivity(intent);
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
