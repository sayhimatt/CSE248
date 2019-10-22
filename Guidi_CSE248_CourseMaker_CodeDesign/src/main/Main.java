package main;

import java.util.ArrayList;

import model.Course;
import model.CourseBag;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Hello";
		String cT = "CSE248";
		String cN = "Advanced Programming";
		String cD = "Advanced Java Object Oriented Programming";
		int cc = 3;
		
		
		ArrayList<Course> prereq1 = new ArrayList<Course>();
		Course testb= new Course("CSE148", cN, cD, cc, prereq1);
		ArrayList<Course> prereq = new ArrayList<Course>();
		Course testC= new Course(cT, cN, cD, cc, prereq);
		prereq.add(testb);
		
		CourseBag cBag = new CourseBag();
		cBag.addCourse(testb);
		cBag.addCourse(testC);
		cBag.pString();
		
	}
	
	

}
