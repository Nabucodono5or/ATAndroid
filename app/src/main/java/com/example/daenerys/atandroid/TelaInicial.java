package com.example.daenerys.atandroid;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

//https://atandroid-d9a01.firebaseapp.com/__/auth/handler
public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    CallbackManager callbackManager;
    Button btnLogin, btnCadastro, btnFacebook;
    private static final String TAG = "facebook";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        mAuth = FirebaseAuth.getInstance();

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //setar um método de vinculaçao com firebase
                Log.e(TAG,"registerCallback:onSuccess");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG,"registerCallback:onError");
            }
        });


        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnFacebook = findViewById(R.id.btnFacebook);

        btnLogin.setOnClickListener(this);
        btnCadastro.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);

    }//onCreate


    private void handleFacebookAccessToken(AccessToken token) {
        if(token != null){

            progressBar.setVisibility(View.VISIBLE);
            AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

            mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.e(TAG,"signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                        Usuario usuario = new Usuario(user.getDisplayName().toString(),"");
                        ref.child(user.getUid()).setValue(usuario);

                        updateUI(user);

                    }else {
                        Log.w(TAG,"signInWithCredential:failure");
                        updateUI(null);
                    }
                }
            });

        }else {
            Log.e(TAG,"token nulo");
            mAuth.signOut();
        }
    }//handleFacebookAccessToken


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }//onActivityResult


    public void updateUI(FirebaseUser user){
        if(user!= null){
            progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
        }
    }//updateUI


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnLogin:
                intent = new Intent(this, TelaLogin.class);
                startActivity(intent);
                break;
            case R.id.btnCadastro:
                intent = new Intent(this,TelaCadastro.class);
                startActivity(intent);
                break;
            case R.id.btnFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        }//switch
    }//onClick
}//class
