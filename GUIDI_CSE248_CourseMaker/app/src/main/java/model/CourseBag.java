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
    public Course getValueFromBag(String key){
        return this.courseBag.get(key);
    }

    public String giveCourseString(Course c){
        return c.toString();
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
    public String toString(){
        String s = "";
        for(Course c : courseBag.values()){
          s += c.toString();
        };
        return s;
    }
    public Course removeCourse(String c){
        return courseBag.remove(c);
    }
    public int getSize(){
        return this.courseBag.size();
    }
}