package model;

import java.util.ArrayList;

public class Course {
	private String courseNumber;
	private String courseTitle;
	private String courseDesc;
	private int courseCredit;
	private ArrayList<Course> prereq = new ArrayList<Course>();
	public Course(String cN, String cT, String cD, int cc, ArrayList<Course> prereq) {
		this.courseNumber = cN;
		this.courseTitle = cT;
		this.courseDesc = cD;
		this.courseCredit = cc;
		this.prereq = prereq;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public int getCourseCredit() {
		return courseCredit;
	}
	public ArrayList<Course> getPrereq() {
		return prereq;
	}
	public String toString() {
		String s = "";
		s += this.getCourseNumber() + " " + this.getCourseTitle() + " ";
		s += this.getCourseDesc() + " " + this.getCourseCredit();
		if(this.prereq.size() > 0) {
		s += "\nprerequisite:\n";
		for(int i = 0; i < this.prereq.size(); i++) {
			s += this.prereq.get(i).getCourseNumber() + "\n";
		}
		}
		return s;
	}

}
