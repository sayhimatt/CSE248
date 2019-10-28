package model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class SemesterBag {
    private ArrayList<Semester> semesterBag;
    private double creditsOverall;
    private boolean canGraduate;
    private static String infoOnScience = "Students who take the BIO or CHE sequence are required to take a third laboratory science course.";
    private static String infoOnHistory = "Students planning to transfer to a SUNY four-year institution are strongly advised to choose American History as their social sciences elective\nDon't pick anything else... please";
    private CourseBag transferCourses;


    private final int NUM_OF_SCIENCE_NEEDED = 6;
    private int numOfScienceTaken;

    private final double NUM_OF_HUM_NEEDED = 3;
    private double numOfHumanitiesTaken;

    private final double NUM_OF_GYM_NEEDED = 2;
    private double numOfGymTaken;


    private static final ArrayList<String> approvedMathL = new ArrayList<String>(Arrays.asList("MAT141","MAT142","MAT205","MAT210"));
    private static final ArrayList<String> approvedLangL = new ArrayList<String>(Arrays.asList("ASL101","ASL105","CHI101","CHI102","FRE101", "FRE102", "FRE201", "FRE202",
            "GER101", "GER102", "GER201", "GER202", "ITL101", "ITL102", "ITL113", "ITL201", "ITL202", "ITL220",
            "JPN101", "JPN102", "JPN201", "JPN202", "LAT101", "LAT102", "SPN101", "SPN102", "SPN113", "SPN126", "SPN127",
            "SPN201", "SPN202", "SPN220", "SPN223", "SPN224"));
    private static final ArrayList<String> approvedEngL = new ArrayList<String>(Arrays.asList("ENG101","ENG102"));
    private static final ArrayList<String> approvedHisL = new ArrayList<String>(Arrays.asList(
            "HIS101", "HIS102","HIS103","HIS104","HIS118","HIS119","HIS120"
    ));
    private static final ArrayList<String> approvedPEDL = new ArrayList<String>(Arrays.asList("PED112", "PED113","PED114","PED115","PED116","PED118","PED119",
            "PED120","PED121","PED123","PED124","PED125","PED126","PED128","PED129","PED130","PED132","PED133","PED134","PED137","PED138","PED141","PED144","PED145",
            "PED146","PED147","PED148","PED149","PED150","PED151","PED155","PED156","PED157","PED159","PED160","PED161","PED162","PED163","PED165","PED166","PED168","PED174",
            "PED175","PED176","PED177","PED190","PED191","PED201","PED202","PED203","PED210","PED295"));
    public static final ArrayList<String> approvedSSL = new ArrayList<String>(Arrays.asList("HIS103","HIS104","HIS106","HIS205","HIS225","POL105",
            "HIS101", "HIS102","HIS107","HIS110","HIS201","IND101","IND102","ANT101", "ANT105","ANT203","ANT211","GEO101"));
    private static final ArrayList<String> approvedHumL= new ArrayList<String>(Arrays.asList("ART101", "ART111", "ART112", "ART113",
            "CIN111", "CIN112", "CIN114", "CIN156","COM105", "COM121", "COM131", "COM133", "COM204", "ENG141", "ENG142", "ENG143", "ENG144",
            "ENG177", "ENG202", "ENG205", "ENG206", "ENG209", "ENG210", "ENG211", "ENG212", "ENG213", "ENG214",
            "ENG215", "ENG216", "ENG218", "ENG219", "ENG220", "ENG221", "ENG223", "ENG226","HUM120", "HUM218",
            "IND101", "IND102", "IND123", "MUS101", "MUS206", "MUS210", "PHL101", "PHL104", "PHL105", "PHL107", "PHL111",
            "PHL112", "PHL113", "PHL201", "PHL202", "PHL211", "PHL212", "PHL213", "PHL214", "PHL215", "PHL293",
            "SPN175", "SPN176", "SPN222", "SPN224", "SPN225", "SPN226", "THR211", "THR212"));
    private static final ArrayList<String> approvedSciL= new ArrayList<String>(Arrays.asList(
            "BIO150", "BIO151", "CHE133","CHE134","PHY130","PHY132","PHY230","PHY232","PHY245","PHY246"
    ));
    private static final ArrayList<String> approvedCSEL = new ArrayList<String>(Arrays.asList(
            "CSE118","CSE148","CSE218","CSE222","CSE248"
    ));
    public SemesterBag(int year, int season){
        semesterBag = new ArrayList<Semester>();
        for(int i = 0; i <= (year - 2019); i++){
            for(int j = 0; j < season; j++){
                String seasonS = "";
                switch(j){
                    case 0:
                        seasonS = "Summer";
                        break;
                    case 1:
                        seasonS = "Fall";
                        break;
                    case 2:
                        seasonS = "Spring";
                        break;
                }
                String yearS = String.valueOf(2019 + i);
                semesterBag.add(new Semester(yearS, seasonS));
            }
        }
        transferCourses = new CourseBag();
    }
    public boolean removeCourse(String courseNumber){
        boolean found = false;
        for(int i = 0; i < semesterBag.size(); i++){
            if(semesterBag.get(i).hasCourse(courseNumber)){
                this.creditsOverall -= semesterBag.get(i).getTheSemestersCourses().getCourseByTitle(courseNumber).getCourseCredit();
                semesterBag.get(i).removeCourseForSemester(courseNumber);
                found = true;
            }
        }
        if(found) {
            for (int i = 0; i < semesterBag.size(); i++) {
                creditsOverall -= semesterBag.get(i).deletePreReq(courseNumber);
            }
        }
        return found;
    }
    public boolean removeTransferCourse(String courseNumber){
        boolean found = false;
        if(transferCourses.getCourseByTitle(courseNumber) != null){
            found = true;
            this.creditsOverall -= transferCourses.removeCourse(courseNumber).getCourseCredit();
        }
        if(found){
            for (int i = 0; i < semesterBag.size(); i++) {
                creditsOverall -= semesterBag.get(i).deletePreReq(courseNumber);
            }
        }
        return found;
    }
    public boolean isCourseBeingTaken(String courseName){
        for(int i = 0; i < semesterBag.size();i++){
            if(semesterBag.get(i).hasCourse(courseName)){
                return true;
            }
        }
        return false;
    }
    public Course getCourseBeingTaken(String courseName){
        for(int i = 0; i < semesterBag.size();i++){
            if(semesterBag.get(i).hasCourse(courseName)){
                return semesterBag.get(i).getTheSemestersCourses().getValueFromBag(courseName);
            }
        }
        return null;
    }
    public void addTransferCourse(Course c){
        this.creditsOverall += c.getCourseCredit();
        transferCourses.addCourse(c);
    }
    public boolean addCourse(String year, String season, Course c){
        int semesterToInsert = 0;
        for(int i = 0; i < semesterBag.size(); i++){
            if(semesterBag.get(i).getYear().equalsIgnoreCase(year) && semesterBag.get(i).getSeason().equalsIgnoreCase(season)){
                semesterToInsert = i;
                i = semesterBag.size();
            }
        }
        ArrayList<String> coursesNeeded = c.getPrereq();
        int countNeeded = coursesNeeded.size();
        int countGot = 0;
        for(int i = 0; i < coursesNeeded.size(); i++){
            Log.println(Log.INFO, "Hey this is my comparison", coursesNeeded.get(i));
            if(transferCourses.getCourseByTitle(coursesNeeded.get(i)) != null){
                Log.println(Log.ERROR,"Help!","I went through and got");
                countGot++;
            }
        }
        if(countGot == countNeeded){
            semesterBag.get(semesterToInsert).insertCourseForSemester(c);
            this.creditsOverall += c.getCourseCredit();
            return true;
        }
        for(int i = 0; i < coursesNeeded.size(); i++){
            for(int j = 0; j < semesterToInsert; j++){
                if(semesterBag.get(j).hasCourse(coursesNeeded.get(i))){
                    countGot++;
                }
            }
        }
        if(countNeeded == countGot) {
            semesterBag.get(semesterToInsert).insertCourseForSemester(c);
            this.creditsOverall += c.getCourseCredit();
            return true;
        }else{
            return false;
        }
    }
    public void recountCredits(){
        this.creditsOverall = 0;
        for(int i = 0; i < semesterBag.size(); i++){
            this.creditsOverall += semesterBag.get(i).getCreditInSemester();
        }
    }
    public boolean canIGraduate(){
        // FIRST CHECK SEE IF OVERALL CREDITS >= 64
        if(creditsOverall < 64.0){
            return false;
        }
        //Now check for sum checks first CSE sequence
        for(int i = 0; i < approvedCSEL.size();i++){
            if(!isCourseBeingTaken(approvedCSEL.get(i))){
                return false;
            }
        }
        //Now check for Math list
        for(int i = 0; i < approvedMathL.size(); i++){
            if(!isCourseBeingTaken(approvedMathL.get(i))){
                return false;
            }
        }
        //Now check for English
        if(!isCourseBeingTaken("ENG101")|| !isCourseBeingTaken("ENG102")){
            return false;
        }
        //Now check for Humanities
        for(String courseName : approvedHumL){
            if(isCourseBeingTaken(courseName)){
                numOfHumanitiesTaken += getCourseBeingTaken(courseName).getCourseCredit();
            }
        }
        if(numOfHumanitiesTaken < NUM_OF_HUM_NEEDED){
            return false;
        }
        //Now check for the history electives and we must keep those two if found to pair against social science
        String his1Course = "";
        for(String courseName : approvedHisL){
            if(isCourseBeingTaken(courseName)){
                if(his1Course == "") {
                    his1Course = courseName;
                    break;
                }
            }
        }if(his1Course.contentEquals("")){
            return false;
        }
        //Now we check for the social science elective
        boolean goodToGo = false;
        for(String courseName : approvedSSL){
            if(isCourseBeingTaken(courseName)
                    && !(courseName.equals(his1Course))){
                goodToGo = true;
            }
        }if(!goodToGo){
            return false;
        }
        // Check for foreign language
        goodToGo=false;
        for(String courseName : approvedLangL){
            if(isCourseBeingTaken(courseName)){
                goodToGo = true;
                break;
            }
        }if(!goodToGo){
            return false;
        }
        //Check for PED
        double creditsIn = 0;
        for(String courseName : approvedPEDL){
            if(isCourseBeingTaken(courseName)){
                creditsIn += getCourseBeingTaken(courseName).getCourseCredit();
                if(creditsIn >=  2){
                    break;
                }
            }
        }if(creditsIn < 2){
            return false;
        }
        //Check for Science
        // CHE133->CHE134->PHY132
        // BIO150->BIO151->PHY132
        // PHY130->FULL LIST OF PHY in index 4 and onward
        if(isCourseBeingTaken("CHE133")){
            if(isCourseBeingTaken("CHE134")){
                if(isCourseBeingTaken("PHY132")){
                    if(isCourseBeingTaken("PHY132")) {
                        return true;
                    }
                }
            }
        }else if(isCourseBeingTaken("BIO150")){
                if(isCourseBeingTaken("BIO151")){
                    if(isCourseBeingTaken("PHY130")){
                        if(isCourseBeingTaken("PHY132")) {
                            return true;
                        }
                }
            }
        }else if(isCourseBeingTaken("PHY245")){
            if(isCourseBeingTaken("PHY246")){
                return true;
            }
        }
        return false;
    }

    public String getInfoOnScience(){
        return infoOnScience;
    }
    public String getInfoOnHistory(){
        return infoOnHistory;
    }
    public CourseBag getCourseBag(int i){
        return semesterBag.get(i).getTheSemestersCourses();
    }
    public Semester getSemester(int i){
        return semesterBag.get(i);
    }
    public CourseBag getTransferCourses(){
        return transferCourses;
    }
    public double getCreditsOverall() {
        return creditsOverall;
    }
    public boolean isCanGraduate(){
        return canGraduate;
    }
    public ArrayList<String> getApprovedMathL(){
        return approvedMathL;
    }
    public ArrayList<String> getApprovedEngL(){
        return approvedEngL;
    }
    public ArrayList<String> getApprovedLangL(){
        return approvedLangL;
    }
    public ArrayList<String> getApprovedHisL(){
        return approvedHisL;
    }
    public ArrayList<String> getApprovedHumL(){
        return approvedHumL;
    }
    public static ArrayList<String> getApprovedSciL() {
        return approvedSciL;
    }
    @Override
    public String toString(){
        String lineOfDash = "--------------------------------------------------------------------------------------";
        String s = "This is your planned schedule for Suffolk County Community College!";
        s += "\n" + lineOfDash + "\n" + lineOfDash + "\n" + lineOfDash;
        s += "\n\n\n\nYour equivalent transferred courses are the following:\n";
        s += transferCourses.toString();
        s += "\n";
        s+= lineOfDash;
        for(int i = 0; i < this.semesterBag.size(); i++){
            if(this.semesterBag.get(i).getTheSemestersCourses().getSize() >0) {
                s += this.semesterBag.get(i).toString();
                s+= lineOfDash;
            }
        }
        return s;
    }
}
