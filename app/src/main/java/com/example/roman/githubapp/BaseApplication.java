package com.example.roman.githubapp;

import android.app.Application;
import android.app.DownloadManager;

import com.example.roman.githubapp.activities.BaseActivity;
import com.example.roman.githubapp.managers.BaseManager;
import com.example.roman.githubapp.managers.GeneralManager;
import com.example.roman.githubapp.managers.RequestManager;

import java.util.Stack;

/**
 * Created by roman on 29/07/2017.
 */

public class BaseApplication extends Application {

    public static BaseApplication baseApplication;
    private Stack<BaseManager> managerStack;
    private BaseActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        managerStack = new Stack<>();
        createManagers();
    }

    public static BaseApplication getApp() {
        return baseApplication;
    }

    private void createManagers() {
        managerStack.add(getGeneralManager());
        managerStack.add(getRequestManager());
        initManagers();
    }

    public Stack<BaseManager> getManagerStack() {
        return managerStack;
    }

    private void initManagers() {
        for (BaseManager manager : managerStack) {
            manager.initManager();
        }
    }

    public RequestManager getRequestManager() {
        return RequestManager.getRequestManagerInstance(this);
    }

    public GeneralManager getGeneralManager() {
        return GeneralManager.getInstance(this);
    }


    public void setCurrentActivity(BaseActivity activity) {
        this.currentActivity = activity;
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }
}
