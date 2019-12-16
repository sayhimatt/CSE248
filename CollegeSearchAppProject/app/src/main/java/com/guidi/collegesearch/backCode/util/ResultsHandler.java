package com.guidi.collegesearch.backCode.util;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.guidi.collegesearch.backCode.model.School;

import java.util.ArrayList;

public class ResultsHandler {
    public static ArrayList<School> schoolNamesSearchResults = new ArrayList<School>();
    public static boolean nameSDone;
    public ResultsHandler(){

    }
    public void DegreeSchoolSearch(ArrayList<Integer> nums){
        ArrayList<School> newList = new ArrayList<School>();
        for(int i = 0; i < schoolNamesSearchResults.size(); i++){
            for(int j = 0; j < nums.size(); j++){
                if((schoolNamesSearchResults.get(i).getMaxDegree() == nums.get(j)) ||
                        (schoolNamesSearchResults.get(i).getMainDegree() == nums.get(j))){
                    newList.add(schoolNamesSearchResults.get(i));
                    j = nums.size();
                }
            }
        }
        if(nums.size() > 0) {
            schoolNamesSearchResults = newList;
        }
    }
    public void NameSchoolSearch(String nameOID){
        nameSDone = false;
        schoolNamesSearchResults.clear();
        Query q = FirebaseDatabase.getInstance().getReference("schools").orderByChild("collegeName").startAt(nameOID).endAt(nameOID+"\uf8ff");
        q.addListenerForSingleValueEvent(valueEventListener);


    }
    private ValueEventListener valueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    schoolNamesSearchResults.add(snapToSchool(snapshot));
                }
            }
            nameSDone = true;
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
    public static ArrayList<School> getSchoolNamesSearchResults(){
        return schoolNamesSearchResults;
    }
}
