package com.example.guidi_cse248_coursemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Menu mOver;
    private static int nY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        mOver = menu;
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
