package com.guidi.collegesearch.backCode.util;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.main.R;
import com.guidi.collegesearch.frontEnd.mToast;
import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.backCode.model.Username;

import java.util.ArrayList;

public final class OnClickAssigner {
    private static View rootV;
    private static FirebaseAuth mAuth;
    private static Activity mainActivity;
    public static ArrayList <String> schoolNameList;

    public static void setOnClickAssigner(View overView, FirebaseAuth firebaseAuthentication, Activity mActivity) {
        rootV = overView;
        mAuth = firebaseAuthentication;
        mainActivity = mActivity;
    }

    public static void loginHandler() {
        ImageButton loginB = mainActivity.findViewById(R.id.login_image_button);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = ((EditText) rootV.findViewById(R.id.email_edit_text)).getText().toString();
                String pText = ((EditText) rootV.findViewById(R.id.password_edit_text)).getText().toString();
                try {
                    mAuth.signInWithEmailAndPassword(emailText, pText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Login Test", "signInWithEmail:success");
                                        mToast.mT(rootV, "Login successful", false);
                                        loadIt();
                                    } else {
                                        Log.w("Login Test", "signInWithEmail:failure", task.getException());
                                        mToast.mT(rootV, "Sorry mate wrong login info", true);

                                    }
                                }
                            });
                } catch (IllegalArgumentException e) {
                    mToast.mT(rootV,
                            "Sorry mate wrong login info\nAre you missing something?", true);
                }

            }
        });
    }

    public static void registrationHandler() {
        Button rB = mainActivity.findViewById(R.id.register_account_button);
        rB.setOnClickListener(new View.OnClickListener() {
            boolean listener = false;

            @Override
            public void onClick(View v) {
                String email = ((EditText) (rootV.findViewById(R.id.email_address_editText))).getText().toString();
                String password = ((EditText) (rootV.findViewById(R.id.password_one_editText))).getText().toString();
                String password2 = ((EditText) (rootV.findViewById(R.id.password_two_editText))).getText().toString();
                String firstName = ((EditText) (rootV.findViewById(R.id.first_name_editText))).getText().toString();
                String lastName = ((EditText) (rootV.findViewById(R.id.last_name_editText))).getText().toString();
                int satMScore;
                try {
                    satMScore = Integer.parseInt(((EditText) (rootV.findViewById(R.id.sat_math_score_etext))).getText().toString().trim());
                } catch (NumberFormatException e) {
                    satMScore = 800;
                }
                int satWScore;
                try {
                    satWScore = Integer.parseInt(((EditText) (rootV.findViewById(R.id.sat_rw_score_etext))).getText().toString().trim());
                } catch (NumberFormatException e) {
                    satWScore = 800;
                }
                if (email.equals("")) {
                    mToast.mT(v, "Fill in your Email\n ... moron", false);
                } else if (password.equals("")) {
                    mToast.mT(v, "Write your desired password\n ... moron", false);
                } else if (password2.equals("")) {
                    mToast.mT(v, "Confirm the password\n ... moron", false);
                } else if (firstName.equals("")) {
                    mToast.mT(v, "Write your first name", false);
                } else if (lastName.equals("")) {
                    mToast.mT(v, "Write your last name", false);
                }
                if (password.equals(password2)) {
                    final Account myAccount = new Account(new Username(email), firstName, lastName);
                    if (satMScore != 0) {
                        myAccount.setSatMScore(satMScore);
                    } else {
                        myAccount.setSatMScore(800);
                    }
                    if (satWScore != 0) {
                        myAccount.setSatWScore(satWScore);
                    } else {
                        myAccount.setSatWScore(800);
                    }

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                mToast.mT(rootV, "It worked", true);
                                uploadUser(myAccount);


                            } else {
                                // If sign in fails, display a message to the user
                                mToast.mT(rootV, task.getException().toString(), true);
                                listener = false;
                            }

                            // ...
                        }
                    });

                }

            }
        });
    }
    public static void loadIt(){
        mainActivity.setContentView(R.layout.activity_general);
        fillSchoolNameList();
        BottomNavigationView navView = mainActivity.findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) mainActivity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
    private static void uploadUser(Account myAccount) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = mAuth.getCurrentUser().getUid();
        myRef.child(id).setValue(myAccount);
    }
    public static boolean backToLogin() {
        FirebaseAuth.getInstance().signOut();
        mainActivity.setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(mainActivity.findViewById(R.id.main_login_linear_layout), mAuth, mainActivity);
        loginHandler();
        return true;
    }
    public static void fillSchoolNameList(){
        Query q = FirebaseDatabase.getInstance().getReference("schools").orderByChild("collegeName");
        schoolNameList = new ArrayList<String>();
        ValueEventListener valueEventListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        //schoolNameList.add(snapshot.child("collegeName").getValue().toString());
                        schoolNameList.add(snapshot.child("collegeName").getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        q.addListenerForSingleValueEvent(valueEventListener);
    }

}

