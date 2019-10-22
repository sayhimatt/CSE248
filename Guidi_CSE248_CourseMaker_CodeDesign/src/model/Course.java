package model;

import java.util.ArrayList;

public class Course {
	private String courseNumber;
	private String courseTitle;
	private String courseDesc;
	private double courseCredit;
	private ArrayList<String> prereq = new ArrayList<String>();
	public Course(String cN, String cT, String cD, double cc, ArrayList<String> prereq) {
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
	public double getCourseCredit() {
		return courseCredit;
	}
	public ArrayList<String> getPrereq() {
		return prereq;
	}
	public String toString() {
		String s = "";
		s += this.getCourseNumber() + " " + this.getCourseTitle() + " \n";
		s += this.getCourseDesc() + " \n" + this.getCourseCredit();
		if(this.prereq.size() > 0) {
		s += "\nprerequisite:\n";
		for(int i = 0; i < this.prereq.size(); i++) {
			s += this.prereq.get(i) + "\n";
		}
		}
		return s;
	}

}
