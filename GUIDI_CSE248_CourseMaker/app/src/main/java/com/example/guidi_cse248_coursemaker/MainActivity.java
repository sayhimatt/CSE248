package com.example.guidi_cse248_coursemaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import model.Course;
import model.CourseBag;
import model.SemesterBag;
import util.DataLoader;

public class MainActivity extends AppCompatActivity {
    private Menu mOver;
    private static int nY = 2;
    private final String COURSE_FILEPATH = "Course_Inventory.txt";

    private TreeMap<String, Course> courseListOrdered;
    private int selectedSemester;
    private CourseBag cb;
    private SemesterBag sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputStream is = null;
        AssetManager am = this.getAssets();
        try {
             is = am.open(COURSE_FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DataLoader dl = new DataLoader(is);
        selectedSemester = 0;
        cb = new CourseBag();
        sb = new SemesterBag();
        cb.loadData(dl);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        mOver = menu;
        return true;

    }
    public boolean insertCourses(CourseBag cb, String courseSubject){
        ScrollView a = (ScrollView)findViewById(R.id.scroll_view);
        a.removeAllViews();
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        if(courseSubject.equalsIgnoreCase("ALL")) {
            for (Course c : cb.getTList().values()) {
                LinearLayout innerContainer = makeViewForCourseSelection(c);
                outerContainer.addView(innerContainer);
            }
        }else {
            for (int i = 0; i < 999; i++) {
                String cNum = String.format("%03d", i);
                String keyIndex = courseSubject + cNum;
                HashMap<String, Course> hM = cb.getHList();
                if (hM.get(keyIndex) != null) {
                    Course c = hM.get(keyIndex);
                    LinearLayout innerContainer = makeViewForCourseSelection(c);
                    outerContainer.addView(innerContainer);
                }
            }
        }

       a.addView(outerContainer);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        TextView s = findViewById(R.id.semesterTop);
        String season, year;
        switch(item.getItemId()){
            case R.id.summer_2019:
                season = (String)item.getTitle();
                year = "2019";
                s.setText(season + " " + year);
                selectedSemester = 0;
                changeSemesterView();
                return true;
            case R.id.fall_2019:
                season = (String)item.getTitle();
                year = "2019";
                s.setText(season + " " + year);
                selectedSemester = 1;
                changeSemesterView();
                return true;
            case R.id.spring_2019:
                season = (String)item.getTitle();
                year = "2019";
                s.setText(season + " " + year);
                selectedSemester = 2;
                changeSemesterView();
                return true;
            case R.id.summer_2020:
                season = (String)item.getTitle();
                year = "2020";
                s.setText(season + " " + year);
                selectedSemester = 3;
                changeSemesterView();
                return true;
            case R.id.fall_2020:
                season = (String)item.getTitle();
                year = "2020";
                s.setText(season + " " + year);
                selectedSemester = 4;
                changeSemesterView();
                return true;
            case R.id.spring_2020:
                season = (String)item.getTitle();
                year = "2020";
                s.setText(season + " " + year);
                selectedSemester = 5;
                changeSemesterView();
                return true;
            case R.id.summer_2021:
                season = (String)item.getTitle();
                year = "2021";
                s.setText(season + " " + year);
                selectedSemester = 6;
                changeSemesterView();
                return true;
            case R.id.fall_2021:
                season = (String)item.getTitle();
                year = "2021";
                s.setText(season + " " + year);
                selectedSemester = 7;
                changeSemesterView();
                return true;
            case R.id.spring_2021:
                season = (String)item.getTitle();
                year = "2021";
                s.setText(season + " " + year);
                selectedSemester = 8;
                changeSemesterView();
                return true;
            case R.id.summer_2022:
                season = (String)item.getTitle();
                year = "2022";
                s.setText(season + " " + year);
                selectedSemester = 9;
                changeSemesterView();
                return true;
            case R.id.fall_2022:
                season = (String)item.getTitle();
                year = "2022";
                s.setText(season + " " + year);
                selectedSemester = 10;
                changeSemesterView();
                return true;
            case R.id.spring_2022:
                season = (String)item.getTitle();
                year = "2022";
                s.setText(season + " " + year);
                selectedSemester = 11;
                changeSemesterView();
                return true;
            case R.id.summer_2023:
                season = (String)item.getTitle();
                year = "2023";
                s.setText(season + " " + year);
                selectedSemester = 12;
                changeSemesterView();
                return true;
            case R.id.fall_2023:
                season = (String)item.getTitle();
                year = "2023";
                s.setText(season + " " + year);
                selectedSemester = 13;
                changeSemesterView();
                return true;
            case R.id.spring_2023:
                season = (String)item.getTitle();
                year = "2023";
                s.setText(season + " " + year);
                selectedSemester = 14;
                changeSemesterView();
                return true;
            case R.id.allCourses_B:
                displayByCategories("ALL",false);
                return true;
            case R.id.CSE_B:
                displayByCategories("CSE",false);
                return true;
            case R.id.MAT_B:
                displayByCategories("MAT",true);
                return true;
            case R.id.SCI_B:
                displayByCategories("SCI",true);
                return true;
            case R.id.HUM_B:
                displayByCategories("HUM",true);
                return true;
            case R.id.ENG_B:
                displayByCategories("ENG",true);
                return true;
            case R.id.HIS_B:
                displayByCategories("HIS",true);
                return true;
            case R.id.LANG_B:
                displayByCategories("LANG", true);
                return true;
            case R.id.PHE_B:
                displayByCategories("PED", false);
                return true;
            case R.id.CUSTOM_B:
                askForTextInput("Please enter the 3 letter course subject!");
                return true;
            case R.id.add_year:
                System.out.println(nY);
                (mOver.getItem(0).getSubMenu().getItem(nY)).setVisible(true);
                if(nY > 0) {
                    nY--;
                }
                return true;
            default:
                return false;
        }
    }
    public void displayByCategories(String title, boolean onlyForCSEMajor) {
        if (!onlyForCSEMajor) {
            insertCourses(cb, title);
        }else{
            insertCoursesCSE(cb,title);
        }

    }
    public LinearLayout makeViewForCourseSelection(Course c){
        LinearLayout innerContainer = new LinearLayout(this);
        innerContainer.setOrientation(LinearLayout.HORIZONTAL);
        innerContainer.setPadding(20, 20, 20, 20);
        innerContainer.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        TextView courseTitleTV = new TextView(this);
        innerContainer.setMinimumHeight(250);
        final Button addCourseB = new Button(this);
        courseTitleTV.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
        courseTitleTV.setText(c.getCourseTitle() + "\n Credits: " + c.getCourseCredit());
        courseTitleTV.setTextSize(16);
        courseTitleTV.setTypeface(null, Typeface.BOLD);
        addCourseB.setText(c.getCourseNumber());
        addCourseB.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(c.getCourseTitle()+ "\n" + c.getCourseDesc() + "\n" + "Prereq Courses" + c.getPrereq().toString()
                        ).setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(sb.addCourse(sb.getSemester(selectedSemester).getYear(),
                                sb.getSemester(selectedSemester).getSeason(), c)){
                            addCourseB.setText("Added!");
                            addCourseB.setBackgroundColor(Color.GREEN);
                        }else{
                            addCourseB.setText("Error 8(");
                            addCourseB.setBackgroundColor(Color.RED);
                        }
                        changeSemesterView();
                    }
                }).setNegativeButton("Nope",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        addCourseB.setClickable(true);
        if (!addCourseB.isClickable()) {
            addCourseB.setBackgroundColor(Color.GRAY);
            addCourseB.setText("N/A");
        }


        System.out.println(courseTitleTV.getText());

        innerContainer.addView(addCourseB, 0);

        innerContainer.addView(courseTitleTV, 1);

        TooltipCompat.setTooltipText(innerContainer, c.getCourseDesc());
        return innerContainer;
    }
    public void insertCoursesCSE(CourseBag cb,String courseListForCSE){
        ArrayList<String> myListToOutput = new ArrayList<>();
        TextView courseExtra = new TextView(this);
        switch(courseListForCSE){
            case "MAT":
                myListToOutput = cb.getApprovedMathL();
                break;
            case "ENG":
                myListToOutput = cb.getApprovedEngL();
                break;
            case "HIS":
                myListToOutput = cb.getApprovedHisL();
                courseExtra.setText(sb.getInfoOnHistory());
                break;
            case "SCI":
                myListToOutput = cb.getApprovedSciL();
                courseExtra.setText(sb.getInfoOnScience());
                break;
            case "HUM":
                myListToOutput = cb.getApprovedHumL();
                break;
            case "LANG":
                myListToOutput = cb.getApprovedLangL();
                break;
            default:
                break;
        }
        ScrollView a = (ScrollView)findViewById(R.id.scroll_view);
        a.removeAllViews();
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        for (String s : myListToOutput) {
            Course c = cb.getCourseByTitle(s);
            if(c != null) {
                LinearLayout innerContainer = makeViewForCourseSelection(c);
                outerContainer.addView(innerContainer);
            }
        }
       if(courseListForCSE == "SCI" || courseListForCSE == "HIS") {
           outerContainer.addView(courseExtra);
       }
        a.addView(outerContainer);
    }
    public void changeSemesterView(){
        TextView semesterHeader = findViewById(R.id.semesterTop);
        ScrollView sV = findViewById(R.id.semester_scroll);
        CourseBag cbThisSemester = sb.getCourseBag(selectedSemester);
        sV.removeAllViews();
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        for(Course c : cbThisSemester.getHList().values()) {
            if(c != null) {
                LinearLayout oneToAdd = makeViewForCourseSelection(c);
                outerContainer.addView(oneToAdd);
            }
        }
        String seasonS = sb.getSemester(selectedSemester).getSeason();
        String yearS = sb.getSemester(selectedSemester).getYear();
        semesterHeader.setText(seasonS + " " + yearS);
        semesterHeader.append("\t Graduation possible? " + sb.isCanGraduate());
        semesterHeader.append(" " + "\nCredits in semester: " + sb.getSemester(selectedSemester).getCreditInSemester() +  "\tCredits Overall: " + sb.getCreditsOverall());
        sV.addView(outerContainer);
    }
    public void askForTextInput(String messageToAsk){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(messageToAsk);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               final String courseSubToSearch = input.getText().toString();
               displayByCategories(courseSubToSearch, false);
            }
        });
        builder.show();

    }

}
