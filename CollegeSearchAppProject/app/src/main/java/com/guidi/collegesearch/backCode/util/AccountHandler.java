package com.guidi.collegesearch.backCode.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.backCode.model.School;
import com.guidi.collegesearch.backCode.model.Username;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountHandler {
    private static String fName, lName;
    public static boolean doneLoading;
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
        doneLoading = true;

    }
    public static void loadAccount(){
        doneLoading = false;
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference actRef = FirebaseDatabase.getInstance().getReference("users/"+id);
        actRef.addListenerForSingleValueEvent(valueEventListener);
    }
    public static Account getCurrentAccount(){
        return currentAccount;
    }
    public static void setCurrentAccount(Account a){
        currentAccount = a;
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
    public static void refreshSchoolDatabase(Context context){
        SchoolDataPuller dp = new SchoolDataPuller();
        dp.reformTheDatabase(context);
    }




}
