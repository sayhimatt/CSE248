package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import model.Course;

public class DataLoader {
    private File fileToRead;
    private BufferedReader input;
    public DataLoader(InputStream iS){

            InputStreamReader iSR = new InputStreamReader(iS);
            input = new BufferedReader(iSR);

    }
    public String readString() {
        String s = "";
        try {
            s += input.readLine();

        } catch (IOException e) {

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
        if(cN == ""){
            cN = "No course name :( Click for more info!";
        }
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