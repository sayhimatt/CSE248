package com.example.guidi_cse248_coursemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Course;
import model.CourseBag;
import util.DataLoader;

public class MainActivity extends AppCompatActivity {
    private Menu mOver;
    private static int nY = 2;
    private final String COURSE_FILEPATH = "Course_Inventory.txt";
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
        insertCourses(cb);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        mOver = menu;
        return true;

    }
    public boolean insertCourses(CourseBag cb){
        ScrollView a = (ScrollView)findViewById(R.id.scroll_view);
        TreeMap<String, Course> tM = cb.getList();

        LinearLayout outerContainer = new LinearLayout(this);
        outerContainer.setOrientation(LinearLayout.VERTICAL);
        for(Course c : tM.values()) {
            LinearLayout innerContainer = new LinearLayout(this);
            innerContainer.setOrientation(LinearLayout.HORIZONTAL);
            innerContainer.setPadding(12,12,12,12);
            TextView abba = new TextView(this);
            final Button myButton = new Button(this);
            abba.setText(c.getCourseTitle());
            myButton.setText(c.getCourseNumber());
                myButton.setOnClickListener(new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v){
                        myButton.setText("Good");
                    }
                });
            myButton.setClickable(true);
            if(!myButton.isClickable()){
                myButton.setBackgroundColor(Color.GRAY);
                myButton.setText("N/A");
            }
            innerContainer.addView(myButton);
            innerContainer.addView(abba);
            TooltipCompat.setTooltipText(innerContainer, c.getCourseDesc());
           outerContainer.addView(innerContainer);
        }
       a.addView(outerContainer);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.fall_2019:
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
}
