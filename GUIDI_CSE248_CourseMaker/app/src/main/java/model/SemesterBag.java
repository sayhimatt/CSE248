package model;

import java.util.ArrayList;

public class SemesterBag {
    private ArrayList<Semester> semesterBag;
    private double creditsOverall;
    private boolean canGraduate;
    private static String infoOnScience = "Students who take the BIO or CHE sequence are required to take a third laboratory science course.";
    private static String infoOnHistory = "Students planning to transfer to a SUNY four-year institution are strongly advised to choose American History as their social sciences elective\nDon't pick anything else... please";

    public SemesterBag(){
        Semester ss2019 = new Semester("2019","Summer"); Semester f2019 = new Semester("2019","Fall"); Semester s2019 = new Semester("2019", "Spring");
        Semester ss2020 = new Semester("2020","Summer");Semester f2020 = new Semester("2020","Fall");Semester s2020 = new Semester("2020", "Spring");
        Semester ss2021 = new Semester("2021","Summer");Semester f2021 = new Semester("2021","Fall");Semester s2021 = new Semester("2021", "Spring");
        Semester ss2022 = new Semester("2022","Summer");Semester f2022 = new Semester("2022","Fall");Semester s2022 = new Semester("2022", "Spring");
        Semester ss2023 = new Semester("2023","Summer");Semester f2023 = new Semester("2023","Fall");Semester s2023 = new Semester("2023", "Spring");
        semesterBag = new ArrayList<Semester>();
        semesterBag.add(ss2019);semesterBag.add(f2019);semesterBag.add(s2019);
        semesterBag.add(ss2020);semesterBag.add(f2020);semesterBag.add(s2020);
        semesterBag.add(ss2021);semesterBag.add(f2021);semesterBag.add(s2021);
        semesterBag.add(ss2022);semesterBag.add(f2022);semesterBag.add(s2022);
        semesterBag.add(ss2023);semesterBag.add(f2023);semesterBag.add(s2023);
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
    public boolean isCourseBeingTaken(String courseName){
        for(int i = 0; i < semesterBag.size();i++){
            if(semesterBag.get(i).hasCourse(courseName)){
                return true;
            }
        }
        return false;
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

    public double getCreditsOverall() {
        return creditsOverall;
    }
    public boolean isCanGraduate(){
        return canGraduate;
    }
}
