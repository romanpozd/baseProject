package com.example.roman.githubapp.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roman.githubapp.BaseApplication;
import com.example.roman.githubapp.R;

public class BaseActivity extends AppCompatActivity {


    protected BaseApplication app() {
        return BaseApplication.getApp();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app().setCurrentActivity(this);
    }



    public void pushFragment(Fragment fragmentToPush) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragmentToPush);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
