package model;

public class Semester {
    private CourseBag semesterCourses;
    private String year;
    private String season;
    private int semesterID;
    private double creditInSemester;
    public Semester(String year, String season){
        this.year = year;
        this.season = season;
        this.semesterID = (4*Integer.parseInt(year));

        switch(season){
            // So when you do (semesterID/4) the
            // remainder 0.75 is Spring, 0.5 for Fall, and 0.25 for Summer
            case "Spring":
                this.semesterID+=3;
                break;
            case "Fall":
                this.semesterID+=2;
                break;
            case "Summer":
                this.semesterID+=1;

        }
        semesterCourses = new CourseBag();
        creditInSemester = 0;
    }
    public void insertCourseForSemester(Course c){
        semesterCourses.addCourse(c);
        creditInSemester += c.getCourseCredit();
    }
    public void removeCourseForSemester(String courseN){
        creditInSemester -= semesterCourses.removeCourse(courseN).getCourseCredit();
    }


}
