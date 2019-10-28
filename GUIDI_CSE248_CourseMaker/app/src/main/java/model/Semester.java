package model;

import java.util.ArrayList;

public class Semester {
    private CourseBag semesterCourses;
    private String year;
    private String season;
    private int semesterID;
    private double creditInSemester;
    public Semester(String year, String season){
        this.year = year;
        this.season = season;
        this.semesterID = (3*Integer.parseInt(year));

        switch(season){
            // So when you do (semesterID/3)
            case "Spring":
                this.semesterID+=2;
                break;
            case "Fall":
                this.semesterID+=1;
                break;
            case "Summer":
                this.semesterID+=0;

        }
        semesterCourses = new CourseBag();
        creditInSemester = 0;
    }
    public void insertCourseForSemester(Course c){
        semesterCourses.addCourse(c);
        creditInSemester += c.getCourseCredit();
    }
    public boolean hasCourse(String key){
        if(semesterCourses.getValueFromBag(key) != null){
            return true;
        }else{
            return false;
        }
    }
    public String getYear(){
        return year;
    }
    public String getSeason(){
        return season;
    }
    public CourseBag getTheSemestersCourses(){
        return this.semesterCourses;
    }
    public double deletePreReq(String preReqCourse){
        boolean deletedOne = false;
        double creditsGone = 0;
        for(Course c : semesterCourses.getHList().values()){
            if(c.getPrereq().contains(preReqCourse)){

                creditsGone += c.getCourseCredit();
                String courseNum = c.getCourseNumber();
                semesterCourses.removeCourse(courseNum);
                deletedOne = true;

            }
        }
        creditInSemester -= creditsGone;
        return creditsGone;
    }
    public ArrayList<String> removeCourseForSemester(String courseN){
        ArrayList<String> preList = semesterCourses.getCourseByTitle(courseN).getPrereq();
        creditInSemester -= semesterCourses.removeCourse(courseN).getCourseCredit();
        return preList;
    }
    public double getCreditInSemester(){
        return creditInSemester;
    }
    @Override
    public String toString(){
        String s = "\nYour classes for the " + getSeason() + " " + getYear() + " Semester are the following";
        if(semesterCourses.getSize() ==0){
            s += "\n You aren't taking anything for this semester!\n";
        }
        s += semesterCourses.toString();
        return s;
    }


}
