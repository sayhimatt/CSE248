package model;

import java.util.ArrayList;
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
    public TreeMap<String,Course> getList() {
        TreeMap<String, Course> sortedList = new TreeMap<>();
        sortedList.putAll(this.courseBag);
        for(Map.Entry<String, Course> entry : sortedList.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue().toString());
        }
        return sortedList;
    }
    public String giveCourseString(Course c){
        return c.toString();
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
    public int getSize(){
        return this.courseBag.size();
    }
}