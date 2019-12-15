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
    private String fName, lName;
    private Username username;
    private int satMScore, satRScore;
    private static Account currentAccount;
    public AccountHandler(){
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference actRef = FirebaseDatabase.getInstance().getReference("users").child(u.getUid());
        actRef.addListenerForSingleValueEvent(valueEventListener);
    }
    private ValueEventListener valueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    snapToAccount(snapshot);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    private void snapToAccount(DataSnapshot dataSnapshot){
        username = new Username(dataSnapshot.child("username").getValue().toString());
        fName = dataSnapshot.child("firstName").getValue().toString();
        lName = dataSnapshot.child("lastName").getValue().toString();
        satMScore = Integer.valueOf(dataSnapshot.child("satMScore").getValue().toString());
        satRScore = Integer.valueOf(dataSnapshot.child("satRScore").getValue().toString());
        currentAccount = new Account(username, fName, lName, satMScore, satRScore);
    }
    public static Account getCurrentAccount(){
        Log.d("WHAT I GOT" , currentAccount.toString());
        return currentAccount;
    }
}
