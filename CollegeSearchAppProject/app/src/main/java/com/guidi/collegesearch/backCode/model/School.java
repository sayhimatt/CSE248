package com.guidi.collegesearch.backCode.model;

import com.guidi.collegesearch.backCode.util.AccountHandler;
import com.guidi.collegesearch.backCode.util.Degree;
import com.guidi.collegesearch.backCode.util.Region;
import com.guidi.collegesearch.backCode.util.State;

public class School {
    private int id, costIn, costOut, regID, studentSize, mainDegree, maxDegree;
    private String collegeName, city, stateAbr, schoolURL, zip;
    private double admRate, satW25, satW75, satM25,
            satM75, satR25, satR75;

    public School() {

    }


    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getCostIn() {
        return costIn;
    }

    public void setCostIn(String costIn) {
        if (costIn == "null") {
            this.costIn = 0;
        } else {
            this.costIn = Integer.parseInt(costIn);
        }
    }

    public int getCostOut() {
        return costOut;
    }

    public void setCostOut(String costOut) {
        if (costOut == "null") {
            this.costOut = 0;
        } else {
            this.costOut = Integer.parseInt(costOut);
        }
    }

    public Double getSatW25() {
        return satW25;
    }

    public void setSatW25(String satW25) {
        if (satW25 == "null") {
            this.satW25 = 0;
        } else {
            this.satW25 = Double.parseDouble(satW25);
        }
    }

    public Double getSatW75() {
        return satW75;
    }

    public void setSatW75(String satW75) {
        if (satW75 == "null") {
            this.satW75 = 0;
        } else {
            this.satW75 = Double.parseDouble(satW75);
        }
    }

    public Double getSatM25() {
        return satM25;
    }

    public void setSatM25(String satM25) {
        if (satM25 == "null") {
            this.satM25 = 0;
        } else {
            this.satM25 = Double.parseDouble(satM25);
        }
    }

    public Double getSatM75() {
        return satM75;
    }

    public void setSatM75(String satM75) {
        if (satM75 == "null") {
            this.satM75 = 0;
        } else {
            this.satM75 = Double.parseDouble(satM75);
        }
    }

    public Double getSatR25() {
        return satR25;
    }

    public void setSatR25(String satR25) {
        if (satR25 == "null") {
            this.satR25 = 0;
        } else {
            this.satR25 = Double.parseDouble(satR25);
        }
    }

    public Double getSatR75() {
        return satR75;
    }

    public void setSatR75(String satR75) {
        if (satR75 == "null") {
            this.satR75 = 0;
        } else {
            this.satR75 = Double.parseDouble(satR75);
        }
    }

    public int getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        if (regID == "null") {
            this.regID = 0;
        } else {
            this.regID = Integer.parseInt(regID);
        }
    }

    public int getStudentSize() {
        return studentSize;
    }

    public void setStudentSize(String studentSize) {
        if (studentSize == "null") {
            this.studentSize = 0;
        } else {
            this.studentSize = Integer.parseInt(studentSize);
        }
    }

    public int getMainDegree() {
        return mainDegree;
    }

    public void setMainDegree(String mainDegree) {
        if (mainDegree == "null") {
            this.mainDegree = 0;
        } else {
            this.mainDegree = Integer.parseInt(mainDegree);
        }
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(String maxDegree) {
        if (maxDegree == "null") {
            this.maxDegree = 0;
        } else {
            this.maxDegree = Integer.parseInt(maxDegree);
        }
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbr() {
        return stateAbr;
    }

    public void setStateAbr(String stateAbr) {
        this.stateAbr = stateAbr;
    }

    public String getSchoolURL() {
        return schoolURL;
    }

    public void setSchoolURL(String schoolURL) {
        this.schoolURL = schoolURL;
    }

    public double getAdmRate() {
        return admRate;
    }

    public void setAdmRate(String admRate) {
        if (admRate == "null") {
            this.admRate = 0.0;
        } else {
            this.admRate = Double.parseDouble(admRate);
        }
    }
    public static String schoolInfoToString(School cSch){
        String s = "Unique ID: " + cSch.getId()+"\n"+
                "State: " + (State.valueOfAbbreviation(cSch.getStateAbr())).toString()+"\n"+
                "City: " + cSch.getCity()+"\n"+
                "Zip: " + cSch.getZip()+"\n"+
                "Region: " + Region.getRegionS(cSch.getRegID())+"\n";
        if(cSch.getAdmRate() != 0) {
            s += "Admissions Rate: " + ((int) (cSch.getAdmRate() * 100)) + "%\n";
        }else{
            s += "Admissions Rate: " + (100) + "%\n";
        }
        s +=    "Cost In-State: $" + cSch.getCostIn()+"\n"+
                "Cost Out-of-State: $" + cSch.getCostOut()+"\n"+
                "Primary Degree Awarded: " + Degree.getdNameByKey(cSch.getMainDegree())+"\n"+
                "Maximum Degree Awarded: " + Degree.getdNameByKey(cSch.getMaxDegree())+"\n";
        if(cSch.getSchoolURL().equals("null")){
            s+= "School URL is not provided sorry :("+"\n";
        }else {
            s += "School URL: " + cSch.getSchoolURL() + "\n";
        }
        if(cSch.getStudentSize() == 0){
            s += "Student Size is not provided sorry :("+"\n";
        }else {
            s += "Student Size: " + cSch.getStudentSize() + "\n";
        }

        Account a  = AccountHandler.getCurrentAccount();
        int oldMScore = a.getSatMScore();
        int oldWScore = a.getSatRScore();
        int oldRScore = a.getSatRScore();

        int qualityPoints = 0;
        s+= "Your converted SAT Scores are approximately \n"+
                " Math: " + oldMScore + " Reading: " + oldRScore + " Writing: " + oldWScore + "\n";
        if((cSch.getSatM25() != 0) && (cSch.getSatM75() != 0)) {
            s += "Their Math SAT Scores: \n75th percentile: " + cSch.getSatM75() + "\n25th percentile: " + cSch.getSatM25() + "\n";
            if (cSch.getSatM75() < oldMScore) {
                qualityPoints+= 2;
            }else if(cSch.getSatM25() < oldWScore){
                qualityPoints++;
            }else{
                qualityPoints-= 2;
            }
        }
        if((cSch.getSatR25() != 0) && (cSch.getSatR75() != 0)){
            s += "Their Reading SAT Scores: \n75th percentile: " + cSch.getSatR75() + "\n25th percentile: " + cSch.getSatR25() +"\n";
            if (cSch.getSatR75() < oldRScore) {
                qualityPoints+= 2;
            }else if(cSch.getSatR25() < oldRScore){
                qualityPoints++;
            }else{
                qualityPoints-= 2;
            }
        }
        if((cSch.getSatW25() != 0) && (cSch.getSatW75() != 0)) {
            s += "Their Writing SAT Scores: \n75th percentile: " + cSch.getSatW75() + "\n25th percentile: " + cSch.getSatW25() + "\n";
            if (cSch.getSatM75() < oldMScore) {
                qualityPoints+= 2;
            }else if(cSch.getSatM25() < oldWScore){
                qualityPoints++;
            }else{
                qualityPoints-= 2;
            }
        }
        if(qualityPoints < 0){
            s+= "Your odds of being accepted are grim\n*BASED ON INFO PROVIDED*\n";
        }else if(qualityPoints > 0 && qualityPoints <= 3){
            s += "Your odds of being accepted are moderate\n*BASED ON INFO PROVIDED*\n";
        }else if(qualityPoints > 3){
            s+= ("Your odds of being accepted are very likely!\n*BASED ON INFO PROVIDED*\n");
        }
        return s;
    }
}
