package com.guidi.collegesearch.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import custom_views.CheckboxSpinner;
import util.OnClickAssigner;
import util.State;

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
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth, this);
        OnClickAssigner.loginHandler();

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //mToast.mT(findViewById(R.id.main_login_linear_layout), "Sorry something went wrong you'll have to sign in again", true);
                // Display the login page
            }
        });
        if(mAuth.getCurrentUser() != null) {
            Log.d("whoAmISignedInAs?", mAuth.getCurrentUser().getEmail());
            goToMainActivity(this.findViewById(R.id.main_login_linear_layout));

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
    public void addStates(){
        CheckboxSpinner stateSpinner = (CheckboxSpinner)findViewById(R.id.select_state_spinner);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, State.getStateNameList());
        stateSpinner.setAdapter(itemsAdapter);
        stateSpinner.setItems(State.getStateNameList());
        stateSpinner.setSelection(State.values().length - 1);
        stateSpinner.setPrompt("State:");
    }
    public boolean goToMainActivity(View v){
        //setContentView()

        setContentView(R.layout.activity_main);
        addStates();
        return true;
    }
    public boolean backToLogin(View v){
        setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth, this);
        loginHandler();
        return true;
    }
    public boolean registerAccount(View v){
        setContentView(R.layout.activity_register);
        Button rButton = (Button)findViewById(R.id.register_account_button);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.root_registration_view), mAuth, this);
        registrationHandler();

        return true;
    }

}
