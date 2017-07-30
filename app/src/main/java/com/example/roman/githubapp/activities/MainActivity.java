package com.example.roman.githubapp.activities;

import android.os.Bundle;

/**
 * Created by roman on 29/07/2017.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app().getGeneralManager().sendGetUsersRequest();
    }
}
