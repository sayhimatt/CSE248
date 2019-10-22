package main;

import java.io.IOException;
import java.util.ArrayList;

import model.Course;
import model.CourseBag;
import util.DataLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = "Hello";
		String cT = "CSE248";
		String cN = "Advanced Programming";
		String cD = "Advanced Java Object Oriented Programming";
		int cc = 3;
		
		
		ArrayList<String> prereq1 = new ArrayList<String>();
		Course testb= new Course("CSE148", cN, cD, cc, prereq1);
		ArrayList<String> prereq = new ArrayList<String>();
		Course testC= new Course(cT, cN, cD, cc, prereq);
		prereq.add("CSE148");
		
		CourseBag cBag = new CourseBag();
		cBag.addCourse(testb);
		cBag.addCourse(testC);
		cBag.pString();
		
		System.out.println("Now the reading test");
		DataLoader dL = new DataLoader("data/Course_Inventory.txt");
		int i = 0;
		Course c;
		while((i < 300))
		{
			
			
			i++;
			c = dL.readCourse();
			if(c == null) {
				break;
			}
			System.out.println(c.toString());
			
		}
	}
	
	

}
