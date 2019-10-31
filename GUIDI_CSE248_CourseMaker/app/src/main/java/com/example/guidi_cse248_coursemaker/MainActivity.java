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
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
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
    private LinearLayout allListView;
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
        cb = new CourseBag();
        sb = new SemesterBag(2023,3);
        cb.loadData(dl);
        dl.closeScanner();
        allListView = makeAllList(cb);
        assumeAllRequirementsMade();
        //displayByCategories("ALL",false);
        assignClasses();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        mOver = menu;
        return true;

    }
    public LinearLayout makeAllList(CourseBag cb){
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        for (Course c : cb.getTList().values()) {
                LinearLayout innerContainer = makeViewForCourseSelection(c, false);
                outerContainer.addView(innerContainer);
        }
        return outerContainer;
    }
    public boolean insertCourses(CourseBag cb, String courseSubject){
        courseSubject = courseSubject.toUpperCase();
        ScrollView a = (ScrollView)findViewById(R.id.scroll_view);
        a.removeAllViews();
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        if(courseSubject.equalsIgnoreCase("ALL")) {
            outerContainer = this.allListView;
        }else {
            for (int i = 0; i < 999; i++) {
                String cNum = String.format("%03d", i);
                String keyIndex = courseSubject + cNum;
                HashMap<String, Course> hM = cb.getHList();
                if (hM.get(keyIndex) != null) {
                    Course c = hM.get(keyIndex);
                    LinearLayout innerContainer = makeViewForCourseSelection(c, false);
                    outerContainer.addView(innerContainer);
                }
            }
        }
        if(outerContainer.getChildCount() <1 ){
            TextView youScrewedUp = new TextView(this);
            youScrewedUp.setGravity(Gravity.CENTER_HORIZONTAL);
            youScrewedUp.setText("Oh no... you didn't look for the right thing!\nNOTHING came up!");
            youScrewedUp.setTextSize(32);
            outerContainer.addView(youScrewedUp);
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
            case R.id.transferC_B:
                selectedSemester = -1;
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
    public LinearLayout makeViewForCourseSelection(Course c, boolean alreadyIn){
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
                String messageOutput = c.getCourseTitle() + "\n\n" + c.getCourseDesc() + "\n\n" + "Prerequisites:\n";
                if (c.getPrereq().size() > 0) {
                    messageOutput += c.getPrereq().toString();
                } else {
                    messageOutput += "None!";
                }
                if (!alreadyIn) {
                    if(selectedSemester <0){
                        selectedSemester = 0;
                    }
                    builder.setMessage(messageOutput).setPositiveButton("Add Course", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ((sb.getSemester(selectedSemester).getCreditInSemester() + c.getCourseCredit()) > 21) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("You cannot add this course to this semester\n Too many credits!").setNeutralButton("Ok", null);
                                AlertDialog unableToAddA = builder.create();
                                unableToAddA.show();
                            } else if (!(sb.isCourseBeingTaken(c.getCourseNumber())) &&
                                    sb.addCourse(sb.getSemester(selectedSemester).getYear(),
                                            sb.getSemester(selectedSemester).getSeason(), c)) {
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("You cannot add this course yet! \nPlease check for prerequisites \nor if you already added this  ....\nYou can read the description to check for what you need!").setNeutralButton("Ok", null);
                                AlertDialog unableToAddA = builder.create();
                                unableToAddA.show();
                            }
                            changeSemesterView();
                        }
                    }).setNegativeButton("Close", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    builder.setMessage(messageOutput).setNegativeButton("Remove?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(selectedSemester == -1){
                                sb.removeTransferCourse(c.getCourseNumber());

                            }else{
                                sb.removeCourse(c.getCourseNumber());
                            }
                            changeSemesterView();
                        }
                    }).setNeutralButton("Close",null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        addCourseB.setClickable(true);

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
                myListToOutput = sb.getApprovedMathL();
                break;
            case "ENG":
                myListToOutput = sb.getApprovedEngL();
                break;
            case "HIS":
                myListToOutput = sb.getApprovedHisL();
                courseExtra.setText(sb.getInfoOnHistory());
                break;
            case "SCI":
                myListToOutput = sb.getApprovedSciL();
                courseExtra.setText(sb.getInfoOnScience());
                break;
            case "HUM":
                myListToOutput = sb.getApprovedHumL();
                break;
            case "LANG":
                myListToOutput = sb.getApprovedLangL();
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
                LinearLayout innerContainer = makeViewForCourseSelection(c, false);
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
        CourseBag cbThisSemester;
        if(selectedSemester == -1){
            cbThisSemester = sb.getTransferCourses();
        }else {
            cbThisSemester = sb.getCourseBag(selectedSemester);
        }
        sV.removeAllViews();
        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        for(Course c : cbThisSemester.getTList().values()) {
            if(c != null) {
                LinearLayout oneToAdd = makeViewForCourseSelection(c, true);
                outerContainer.addView(oneToAdd);
            }
        }
        String seasonS, yearS;
        if(selectedSemester == -1){
            semesterHeader.setText("Courses Transferred in");
        }else {
            seasonS = sb.getSemester(selectedSemester).getSeason();
            yearS = sb.getSemester(selectedSemester).getYear();
            semesterHeader.setText(seasonS + " " + yearS);
            String cGrad;
            if(sb.canIGraduate()){
                cGrad = "Yes you can!";
            }else{
                cGrad = "Not yet!";
            }
            semesterHeader.append("\t Graduation possible? " + cGrad);
            semesterHeader.append(" " + "\nCredits in semester: " + sb.getSemester(selectedSemester).getCreditInSemester() + "\tCredits Overall: " + sb.getCreditsOverall());
        }
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
    public void assumeAllRequirementsMade(){
        ArrayList<Course> assumedTakenClasses = new ArrayList<Course>();
        String [] classesExpectedFromS0 = {"MAT111","MAT124","MAT125","MAT126","CHE100","CHE122","MAT101","RDG099","ESL012"};
        for(int i = 0; i < classesExpectedFromS0.length; i++){
            assumedTakenClasses.add(cb.getCourseByTitle(classesExpectedFromS0[i]));
        }
        for(Course c : assumedTakenClasses){
            sb.addTransferCourse(c);
        }
    }
    public void assignClasses(){
        String [] semesterOne = {"CSE110", "CSE118", "ENG101", "MAT141", "CHE133"};
        String [] semesterTwo = {"CSE148", "ENG102", "CHE134", "MAT142", "PED112"};
        String [] semesterThree = {"CSE218", "HIS101", "ART101","PHY130", "PHY132", "MAT205"};
        String [] semesterFour = {"CSE222", "CSE248", "MAT210", "ITL101", "HIS103", "PED113"};
        for(int i = 0; i < semesterOne.length;i++){
            sb.getSemester(1).insertCourseForSemester(cb.getCourseByTitle(semesterOne[i]));
            sb.getSemester(2).insertCourseForSemester(cb.getCourseByTitle(semesterTwo[i]));
        }
        for(int i = 0; i < semesterThree.length;i++){
            sb.getSemester(4).insertCourseForSemester(cb.getCourseByTitle(semesterThree[i]));
            sb.getSemester(5).insertCourseForSemester(cb.getCourseByTitle(semesterFour[i]));
        }
        sb.recountCredits();
        //changeSemesterView();
    }
    public boolean transferCoursesPrompt(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Enter a course number of each transfer\nSeparate each course with a comma please");
        selectedSemester = -1;
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String transferCourseList = input.getText().toString();
                transferCourseList.trim();
                transferCourseList.replaceAll(" ", "");
                String listToDecrypt = transferCourseList.toUpperCase();
                String [] t = listToDecrypt.split(",");
                for(int i = 0; i < t.length; i++) {
                    if(t[i].matches("[\\w]{3}[\\d]{3}")) {
                        Course courseToAdd = cb.getCourseByTitle(t[i]);
                        if(courseToAdd != null) {
                            sb.addTransferCourse(courseToAdd);
                        }
                    }
                }
                changeSemesterView();

            }
        });
        builder.show();

        return true;
    }
    public boolean exportSchedule(MenuItem item){
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(sb.toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!" + getFilesDir(),
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
