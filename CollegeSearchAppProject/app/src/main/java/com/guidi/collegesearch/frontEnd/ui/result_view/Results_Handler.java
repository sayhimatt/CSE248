package com.guidi.collegesearch.frontEnd.ui.result_view;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.backCode.model.School;

import java.util.ArrayList;

public class Results_Handler {
    public static ArrayList<School> schoolSearchResults = new ArrayList<School>();
    public Results_Handler(){

    }
    public void newSearch(String nameOID){
        schoolSearchResults.clear();
        Query q = FirebaseDatabase.getInstance().getReference("schools").orderByChild("collegeName").startAt(nameOID).endAt(nameOID+"\uf8ff");
        q.addListenerForSingleValueEvent(valueEventListener);

    }
    private ValueEventListener valueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    schoolSearchResults.add(snapToSchool(snapshot));
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    public School snapToSchool(DataSnapshot school){
        School newSchoolToEnter = new School();
        newSchoolToEnter.setId(school.child("id").getValue().toString());
        newSchoolToEnter.setCollegeName(school.child("collegeName").getValue().toString());
        newSchoolToEnter.setCity(school.child("city").getValue().toString());
        newSchoolToEnter.setZip(school.child("zip").getValue().toString());
        newSchoolToEnter.setStateAbr(school.child("stateAbr").getValue().toString());
        newSchoolToEnter.setSchoolURL(school.child("schoolURL").getValue().toString());
        newSchoolToEnter.setCostIn(school.child("costIn").getValue().toString());
        newSchoolToEnter.setCostOut(school.child("costOut").getValue().toString());
        newSchoolToEnter.setSatW25(school.child("satW25").getValue().toString());
        newSchoolToEnter.setSatW75(school.child("satW75").getValue().toString());
        newSchoolToEnter.setSatM25(school.child("satM25").getValue().toString());
        newSchoolToEnter.setSatM75(school.child("satM75").getValue().toString());
        newSchoolToEnter.setSatR25(school.child("satR25").getValue().toString());
        newSchoolToEnter.setSatR75(school.child("satR75").getValue().toString());
        newSchoolToEnter.setRegID(school.child("regID").getValue().toString());
        newSchoolToEnter.setStudentSize(school.child("studentSize").getValue().toString());
        newSchoolToEnter.setMaxDegree(school.child("maxDegree").getValue().toString());
        newSchoolToEnter.setMainDegree(school.child("mainDegree").getValue().toString());
        newSchoolToEnter.setAdmRate(school.child("admRate").getValue().toString());

        return newSchoolToEnter;
    }
    public static ArrayList<School> getSchoolSearchResults(){
        return schoolSearchResults;
    }
}
