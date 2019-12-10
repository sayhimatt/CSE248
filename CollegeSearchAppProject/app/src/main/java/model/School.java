package model;

public class School {
    private int id, costIn, costOut, regID, studentSize, mainDegree, maxDegree;
    private String collegeName, city, stateAbr, schoolURL, zip;
    private double admRate, satW25, satW75, satM25,
            satM75, satR25, satR75;
    public School(){

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
        if(costIn == "null"){
            this.costIn = 0;
        }else {
            this.costIn = Integer.parseInt(costIn);
        }
    }

    public int getCostOut() {
        return costOut;
    }

    public void setCostOut(String costOut) {
        if(costOut == "null"){
            this.costOut = 0;
        }else {
            this.costOut = Integer.parseInt(costOut);
        }
    }

    public Double getSatW25() {
        return satW25;
    }

    public void setSatW25(String satW25) {
        if(satW25 == "null"){
            this.satW25 = 0;
        }else {
            this.satW25 = Double.parseDouble(satW25);
        }
    }

    public Double getSatW75() {
        return satW75;
    }

    public void setSatW75(String satW75) {
        if(satW75 == "null"){
            this.satW75 = 0;
        }else {
            this.satW75 = Double.parseDouble(satW75);
        }
    }

    public Double getSatM25() {
        return satM25;
    }

    public void setSatM25(String satM25) {
        if(satM25 == "null"){
            this.satM25 = 0;
        }else {
            this.satM25 = Double.parseDouble(satM25);
        }
    }

    public Double getSatM75() {
        return satM75;
    }

    public void setSatM75(String satM75) {
        if(satM75 == "null"){
            this.satM75 = 0;
        }else {
            this.satM75 = Double.parseDouble(satM75);
        }
    }

    public Double getSatR25() {
        return satR25;
    }

    public void setSatR25(String satR25) {
        if(satR25 == "null"){
            this.satR25 = 0;
        }else {
            this.satR25 = Double.parseDouble(satR25);
        }
    }

    public Double getSatR75() {
        return satR75;
    }

    public void setSatR75(String satR75) {
        if(satR75 == "null"){
            this.satR75 = 0;
        }else {
            this.satR75 = Double.parseDouble(satR75);
        }
    }

    public int getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        if(regID == "null"){
            this.regID = 0;
        }else {
            this.regID = Integer.parseInt(regID);
        }
    }

    public int getStudentSize() {
        return studentSize;
    }

    public void setStudentSize(String studentSize) {
        if(studentSize == "null"){
            this.studentSize = 0;
        }else {
            this.studentSize = Integer.parseInt(studentSize);
        }
    }
    public int getMainDegree() {
        return mainDegree;
    }

    public void setMainDegree(String mainDegree) {
        if(mainDegree == "null"){
            this.mainDegree = 0;
        }else {
            this.mainDegree = Integer.parseInt(mainDegree);
        }
    }
    public int getMaxDegree(){
        return maxDegree;
    }
    public void setMaxDegree(String maxDegree){
        if(maxDegree == "null"){
            this.maxDegree = 0;
        }else{
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
        if(admRate == "null"){
            this.admRate = 0.0;
        }else{
            this.admRate = Double.parseDouble(admRate);
        }
    }
}
