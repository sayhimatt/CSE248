package com.guidi.collegesearch.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import util.OnClickAssigner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnClickAssigner.loginHandler(findViewById(R.id.login_image_button));

    }
}
