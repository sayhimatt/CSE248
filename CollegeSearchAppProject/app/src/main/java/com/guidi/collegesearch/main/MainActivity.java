package com.guidi.collegesearch.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import util.OnClickAssigner;
import util.ToastyMatt;

import static util.OnClickAssigner.loginHandler;
import static util.OnClickAssigner.registrationHandler;
import static util.OnClickAssigner.setOnClickAssigner;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth);
        OnClickAssigner.loginHandler();
    }
    @Override
    public void onStart(){
        super.onStart();
        //FirebaseUser cUser = mAuth.getCurrentUser();
        //updateUI(cUser);

    }


    public boolean backToLogin(View v){
        setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth);
        loginHandler();
        return true;
    }
    public boolean registerAccount(View v){
        setContentView(R.layout.activity_register);
        Button rButton = (Button)findViewById(R.id.register_account_button);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.root_registration_view), mAuth);
        registrationHandler(findViewById(R.id.root_registration_view), rButton);
        return true;
    }

}
