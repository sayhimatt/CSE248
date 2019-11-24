package com.guidi.collegesearch.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import util.OnClickAssigner;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnClickAssigner.loginHandler(findViewById(R.id.main_login_linear_layout), mAuth);


    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        //updateUI(cUser);

    }


    public boolean backToLogin(View v){
        setContentView(R.layout.activity_login);
        return true;
    }
    public boolean registerAccount(View v){
        setContentView(R.layout.activity_register);
        return true;
    }
}
