package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import model.Course;

public class DataLoader {
	private File fileToRead;
	private BufferedReader input;
	public DataLoader(String filePath){
		try {
			this.fileToRead = new File(filePath);
			FileReader r = new FileReader(filePath);
			input = new BufferedReader(r);
		}catch(FileNotFoundException e) {
			System.out.println("You wrote the wrong file name.... lol!");
			System.exit(0);
		}
		
	}
	public String readString() {
		String s = "";
		try {
			s += input.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public Course readCourse() {
		String cT = readString();
		if(cT == null) {
			return null;
		}else if(cT.compareTo("") == 0) {
			System.out.println();
			cT = readString();
		}
		if(cT.length() > 10) {
			return null;
		}
		String cN = readString();
		String cD = readString();
		ArrayList<String>pR = new ArrayList<String>();
		int bI = cD.lastIndexOf("Prereq");
		if(bI != -1) {
			String temp = cD.substring(bI);
			int dI = temp.indexOf(".");
				if(dI == -1) {
					dI = temp.length();
				}
			String prereqPhrase = temp.substring(0, dI);
			String [] t = prereqPhrase.split(" ");
			for(int i = 0; i < t.length; i++) {
				if(t[i].matches("[\\w]{3}[\\d]{3}")) {
					pR.add(t[i]);
				}
			}
		}
		
		double cc = Double.parseDouble(readString());
		Course c = new Course(cT, cN, cD, cc, pR);
		return c;
	}
	public long getLineCount(){
		long s = 1;
		try {
			s = Files.lines(fileToRead.toPath(), Charset.defaultCharset()).count();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
