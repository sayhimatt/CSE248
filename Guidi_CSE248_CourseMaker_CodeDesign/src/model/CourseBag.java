package model;

import java.util.HashMap;

public class CourseBag {
	private HashMap<String, Course> courseBag;
	public CourseBag() {
		courseBag = new HashMap<String, Course>();
	}
	public void addCourse(Course c) {
		this.courseBag.put(c.getCourseNumber(), c);
	}
	public void pString() {
		courseBag.forEach((key, value) ->{
			System.out.println(value.toString());
		});
		
	}
	

}
