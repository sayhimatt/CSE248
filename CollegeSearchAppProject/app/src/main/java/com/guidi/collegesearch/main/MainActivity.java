package com.guidi.collegesearch.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.backCode.model.School;
import com.guidi.collegesearch.backCode.util.OnClickAssigner;
import com.guidi.collegesearch.backCode.util.AccountHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.guidi.collegesearch.backCode.util.OnClickAssigner.fillSchoolNameList;
import static com.guidi.collegesearch.backCode.util.OnClickAssigner.loginHandler;
import static com.guidi.collegesearch.backCode.util.OnClickAssigner.registrationHandler;

public class MainActivity extends AppCompatActivity {
    private static int maxPageN;
    private static int pageN;
    public static ArrayList <School> whatIGot;

    private static int numOfSchoolsFound;
    private static FirebaseAuth mAuth;
    private FirebaseDatabase userDatabase;
    private RequestQueue testQueue;
    private RequestQueue betterQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        /*loadMainFragments();*/
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //mToast.mT(findViewById(R.id.main_login_linear_layout), "Sorry something went wrong you'll have to sign in again", true);
                // Display the login page

            }
        });
        if (mAuth.getCurrentUser() != null) {
            //Log.d("whoAmISignedInAs?", mAuth.getCurrentUser().getEmail());
            AccountHandler.loadAccount();
            fillSchoolNameList();
            loadMainFragments();
        } else {
            backToLogin(this);
        }

    }

    public  void loadMainFragments() {
        setContentView(R.layout.activity_general);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public boolean goToMainActivity(View v) {
        AccountHandler.loadAccount();
        fillSchoolNameList();
        loadMainFragments();
        return true;
    }
    public static boolean backToLogin(Activity m) {
        FirebaseAuth.getInstance().signOut();
        m.setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(m.findViewById(R.id.main_login_linear_layout), mAuth, m);
        loginHandler();
        return true;
    }
    public boolean backToLogin(View v) {
        setContentView(R.layout.activity_login);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.main_login_linear_layout), mAuth, this);
        loginHandler();
        return true;
    }

    public boolean registerAccount(View v) {
        setContentView(R.layout.activity_register);
        Button rButton = findViewById(R.id.register_account_button);
        OnClickAssigner.setOnClickAssigner(findViewById(R.id.root_registration_view), mAuth, this);
        registrationHandler();
        return true;
    }
}
