package com.example.guidi_cse248_coursemaker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import util.DataLoader;

import static android.graphics.Typeface.BOLD_ITALIC;

public class MainActivity extends AppCompatActivity {
    private Menu mOver;
    private static int nY = 2;
    private final String COURSE_FILEPATH = "Course_Inventory.txt";
    private TreeMap<String, Course> courseListOrdered;
    private CourseBag cb;
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
                LinearLayout innerContainer = makeNewCourseToView(c);
                outerContainer.addView(innerContainer);
            }
        }else {
            for (int i = 0; i < 999; i++) {
                String cNum = String.format("%03d", i);
                String keyIndex = courseSubject + cNum;
                HashMap<String, Course> hM = cb.getHList();
                if (hM.get(keyIndex) != null) {
                    Course c = hM.get(keyIndex);
                    LinearLayout innerContainer = makeNewCourseToView(c);
                    outerContainer.addView(innerContainer);
                }
            }
        }

       a.addView(outerContainer);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.summer_2019:
                return true;
            case R.id.fall_2019:
                return true;
            case R.id.spring_2019:
                return true;
            case R.id.summer_2020:
                return true;
            case R.id.fall_2020:
                return true;
            case R.id.spring_2020:
                return true;
            case R.id.summer_2021:
                return true;
            case R.id.fall_2021:
                return true;
            case R.id.spring_2021:
                return true;
            case R.id.summer_2022:
                return true;
            case R.id.fall_2022:
                return true;
            case R.id.spring_2022:
                return true;
            case R.id.summer_2023:
                return true;
            case R.id.fall_2023:
                return true;
            case R.id.spring_2023:
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
    public LinearLayout makeNewCourseToView(Course c){
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
                addCourseB.setClickable(false);
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
        switch(courseListForCSE){
            case "MAT":
                myListToOutput = cb.getApprovedMathL();
                break;
            case "ENG":
                myListToOutput = cb.getApprovedEngL();
                break;
            case "HIS":
                myListToOutput = cb.getApprovedHisL();
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
                LinearLayout innerContainer = makeNewCourseToView(c);
                outerContainer.addView(innerContainer);
            }
        }
        a.addView(outerContainer);
    }

}
