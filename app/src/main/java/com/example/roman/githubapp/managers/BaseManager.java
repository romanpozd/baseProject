package com.example.roman.githubapp.managers;

import com.example.roman.githubapp.BaseApplication;

/**
 * Created by roman on 29/07/2017.
 */

public  class BaseManager  {

    private static BaseApplication app;

    public BaseManager(BaseApplication baseApplication) {
        app = baseApplication;
    }

    public void initManager(){

    }

    public BaseApplication app() {
        return app;
    }


}
