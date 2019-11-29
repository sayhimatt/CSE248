package com.guidi.collegesearch.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Account;
import model.Username;
import util.OnClickAssigner;
import util.ToastyMatt;

import static util.OnClickAssigner.loginHandler;
import static util.OnClickAssigner.registrationHandler;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth);
        OnClickAssigner.loginHandler();

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                ToastyMatt.makeMToast(findViewById(R.id.main_login_linear_layout), "Sorry something went wrong you'll have to sign in again", true);
                // Display the login page
            }
        });
        if(mAuth.getCurrentUser() != null) {
            Log.d("whoAmISignedInAs?", mAuth.getCurrentUser().getEmail());
            // Display the college search page
        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Override
    public void onStart(){
        super.onStart();



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
