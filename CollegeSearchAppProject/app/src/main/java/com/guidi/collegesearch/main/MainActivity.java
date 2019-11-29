package com.guidi.collegesearch.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import util.OnClickAssigner;
import util.ToastyMatt;

import static util.OnClickAssigner.loginHandler;
import static util.OnClickAssigner.registrationHandler;
import static util.OnClickAssigner.setOnClickAssigner;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseFirestore.setLoggingEnabled(true);
        Log.d("Test error", "what happened");
        DatabaseReference myRef = database.getReference("message");
        Log.d("Test error", "what happened");
        myRef.push().setValue("Hello, World!", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference dataReference) {
                Log.d("Test error", "what happened" + databaseError.getMessage());
            }

        });
        Log.d("Test error", "what happened2222");
        //OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth);
        //OnClickAssigner.loginHandler();

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
