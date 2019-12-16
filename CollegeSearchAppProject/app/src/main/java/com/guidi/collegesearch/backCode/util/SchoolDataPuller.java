package com.guidi.collegesearch.backCode.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.guidi.collegesearch.backCode.model.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SchoolDataPuller {


    private static int pageN = 0;
    private static int maxPageN;
    public static boolean donePulling;
    private RequestQueue outerQueue, innerQueue;
    public void reformTheDatabase(Context context) {
       //donePulling = false;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("schools");
        myRef.removeValue();
        outerQueue = Volley.newRequestQueue(context);
        getTotalPagesToParse();
        final Context c = context;
        outerQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override

            public void onRequestFinished(Request<Object> request) {
                pageN--;
                innerQueue = Volley.newRequestQueue(c);
                innerQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {

                    }
                });
                for (int i = 0; i <= maxPageN; i++) {
                    Log.d("Page #", "" + i);
                    jsonParse();
                }
                //donePulling = true;



            }
        });

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

        outerQueue.add(request);
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
        innerQueue.add(request);

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
}
