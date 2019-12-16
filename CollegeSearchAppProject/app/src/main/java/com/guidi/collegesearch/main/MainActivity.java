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

import static com.guidi.collegesearch.backCode.util.OnClickAssigner.loginHandler;
import static com.guidi.collegesearch.backCode.util.OnClickAssigner.registrationHandler;

public class MainActivity extends AppCompatActivity {
    private static int maxPageN;
    private static int pageN;
    public static ArrayList <School> whatIGot;
    public static ArrayList <String> schoolNameList;
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

    private void reformTheDatabase() {
        numOfSchoolsFound = 0;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("schools");
        myRef.removeValue();
        testQueue = Volley.newRequestQueue(this);
        getTotalPagesToParse();
        testQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override

            public void onRequestFinished(Request<Object> request) {
                pageN--;
                betterQueue = Volley.newRequestQueue(getBaseContext());
                betterQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {
                        Log.d("Wow", "I found this many schools " + numOfSchoolsFound);
                    }
                });
                for (int i = 0; i <= maxPageN; i++) {
                    Log.d("Page #", "" + i);
                    jsonParse();
                }


            }
        });
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

    private void getTotalPagesToParse() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getQueryForm(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject metadata = response.getJSONObject("metadata");
                            int i = metadata.getInt("total");
                            double d = (i / 100);
                            i = (int) (d + 1);
                            maxPageN = i;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        testQueue.add(request);
    }

    private void jsonParse() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getQueryForm(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject metadata = response.getJSONObject("metadata");
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject school = jsonArray.getJSONObject(i);
                                School newSchoolToEnter = new School();
                                try {
                                    newSchoolToEnter.setId(school.getString("id"));
                                    newSchoolToEnter.setCollegeName(school.getString("school.name"));
                                    newSchoolToEnter.setCity(school.getString("school.city"));
                                    newSchoolToEnter.setZip(school.getString("school.zip"));
                                    newSchoolToEnter.setStateAbr(school.getString("school.state"));
                                    newSchoolToEnter.setSchoolURL(school.getString("school.school_url"));
                                    newSchoolToEnter.setCostIn(school.getString("latest.cost.tuition.in_state"));
                                    newSchoolToEnter.setCostOut(school.getString("latest.cost.tuition.out_of_state"));
                                    newSchoolToEnter.setSatW25(school.getString("latest.admissions.sat_scores.25th_percentile.writing"));
                                    newSchoolToEnter.setSatW75(school.getString("latest.admissions.sat_scores.75th_percentile.writing"));
                                    newSchoolToEnter.setSatM25(school.getString("latest.admissions.sat_scores.25th_percentile.math"));
                                    newSchoolToEnter.setSatM75(school.getString("latest.admissions.sat_scores.75th_percentile.math"));
                                    newSchoolToEnter.setSatR25(school.getString("latest.admissions.sat_scores.25th_percentile.critical_reading"));
                                    newSchoolToEnter.setSatR75(school.getString("latest.admissions.sat_scores.75th_percentile.critical_reading"));
                                    newSchoolToEnter.setRegID(school.getString("school.region_id"));
                                    newSchoolToEnter.setStudentSize(school.getString("latest.student.size"));
                                    newSchoolToEnter.setMaxDegree(school.getString("school.degrees_awarded.highest"));
                                    newSchoolToEnter.setMainDegree(school.getString("school.degrees_awarded.predominant"));
                                    newSchoolToEnter.setAdmRate(school.getString("latest.admissions.admission_rate.overall"));
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("schools");
                                    myRef.child(String.valueOf(newSchoolToEnter.getId())).setValue(newSchoolToEnter);
                                    numOfSchoolsFound++;
                                } catch (JSONException e) {

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 200,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        betterQueue.add(request);

    }
    private School dataSnapToSchool(DataSnapshot s){
        School schoolMade = null;
        return schoolMade;
    }
    private String getQueryForm() {
        final String API_KEY = "Afw22hMz3Vc46y5Qps2Zrhr9VcpyEec383DD1dBl";
        int page = pageN;
        String qForm = "https://api.data.gov/ed/collegescorecard/v1/schools.json?&fields=" +
                "id," +
                "school.region_id," +
                "school.name," +
                "school.city," +
                "school.state," +
                "school.zip," +
                "school.school_url," +
                "latest.cost.tuition.in_state," +
                "latest.cost.tuition.out_of_state," +
                "latest.student.size," +
                "latest.admissions.admission_rate.overall," +
                "school.degrees_awarded.predominant," +
                "school.degrees_awarded.highest," +
                "latest.admissions.sat_scores.25th_percentile.critical_reading," +
                "latest.admissions.sat_scores.25th_percentile.math," +
                "latest.admissions.sat_scores.25th_percentile.writing," +
                "latest.admissions.sat_scores.75th_percentile.critical_reading," +
                "latest.admissions.sat_scores.75th_percentile.math," +
                "latest.admissions.sat_scores.75th_percentile.writing," +
                "&api_key=" + API_KEY +
                "&_per_page=100" + "&page=" + page
                + "&sort=school.name";
        pageN++;
        return qForm;
    }

    private void fillSchoolNameList(){
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
