package com.guidi.collegesearch.frontEnd.ui.account_view;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.backCode.model.Username;

public class AccountHandler {
    private static String fName, lName;
    private static Username username;
    private static int satMScore, satRScore;
    private static Account currentAccount;
    public AccountHandler(){

    }

    private static void snapToAccount(DataSnapshot dataSnapshot){

        username = new Username(dataSnapshot.child("username").getValue().toString());
        fName = dataSnapshot.child("firstName").getValue().toString();
        lName = dataSnapshot.child("lastName").getValue().toString();
        satMScore = Integer.valueOf(dataSnapshot.child("satMScore").getValue().toString());
        satRScore = Integer.valueOf(dataSnapshot.child("satRScore").getValue().toString());
        currentAccount = new Account(username, fName, lName, satMScore, satRScore);
        System.out.println("Hi I just finished");

    }
    public static void loadAccount(){
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference actRef = FirebaseDatabase.getInstance().getReference("users/"+id);
        actRef.addListenerForSingleValueEvent(valueEventListener);
    }
    public static Account getCurrentAccount(){
        return currentAccount;
    }
    private static ValueEventListener valueEventListener = new ValueEventListener(){

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                snapToAccount(dataSnapshot);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
