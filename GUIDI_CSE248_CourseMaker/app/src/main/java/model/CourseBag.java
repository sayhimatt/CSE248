package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import util.DataLoader;

public class CourseBag {
    private HashMap<String, Course> courseBag;
    private static final ArrayList<String> approvedMathL = new ArrayList<String>(Arrays.asList("MAT141","MAT142","MAT205","MAT210"));
    private static final ArrayList<String> approvedLangL = new ArrayList<String>(Arrays.asList("ASL101","ASL105","CHI101","CHI102","FRE101", "FRE102", "FRE201", "FRE202",
            "GER101", "GER102", "GER201", "GER202", "ITL101", "ITL102", "ITL113", "ITL201", "ITL202", "ITL220",
    "JPN101", "JPN102", "JPN201", "JPN202", "LAT101", "LAT102", "SPN101", "SPN102", "SPN113", "SPN126", "SPN127",
    "SPN201", "SPN202", "SPN220", "SPN223", "SPN224"));
    private static final ArrayList<String> approvedEngL = new ArrayList<String>(Arrays.asList("ENG101","ENG102"));
    private static final ArrayList<String> approvedHisL = new ArrayList<String>(Arrays.asList(
            "HIS101", "HIS102","HIS103","HIS104","HIS118","HIS119","HIS120"
    ));
    private static final ArrayList<String> approvedHumL= new ArrayList<String>(Arrays.asList("ART101", "ART111", "ART112", "ART113",
            "CIN111", "CIN112", "CIN114", "CIN156","COM105", "COM121", "COM131", "COM133", "COM204","ENG102", "ENG141", "ENG142", "ENG143", "ENG144",
            "ENG177", "ENG202", "ENG205", "ENG206", "ENG209", "ENG210", "ENG211", "ENG212", "ENG213", "ENG214",
            "ENG215", "ENG216", "ENG218", "ENG219", "ENG220", "ENG221", "ENG223", "ENG226","HUM120", "HUM218",
            "IND101", "IND102", "IND123", "MUS101", "MUS206", "MUS210", "PHL101", "PHL104", "PHL105", "PHL107", "PHL111",
            "PHL112", "PHL113", "PHL201", "PHL202", "PHL211", "PHL212", "PHL213", "PHL214", "PHL215", "PHL293",
            "SPN175", "SPN176", "SPN222", "SPN224", "SPN225", "SPN226", "THR211", "THR212"));

    public CourseBag() {
        courseBag = new HashMap<String, Course>();
    }
    public void addCourse(Course c) {
        this.courseBag.put(c.getCourseNumber(), c);
    }
    public TreeMap<String,Course> getTList() {
        TreeMap<String, Course> sortedList = new TreeMap<>();
        sortedList.putAll(this.courseBag);

        return sortedList;
    }
    public HashMap<String, Course> getHList(){
        return this.courseBag;
    }

    public String giveCourseString(Course c){
        return c.toString();
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
    public Course getCourseByTitle(String title){
        return courseBag.get(title);
    }
    public boolean loadData(DataLoader dl) {
        Course c;
        do{

            c = dl.readCourse();
            if(c != null) {
                this.addCourse(c);
            }
        }while(c != null);



        return true;
    }
    public Course removeCourse(String c){
        return courseBag.remove(c);
    }
    public int getSize(){
        return this.courseBag.size();
    }
}