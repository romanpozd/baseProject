package com.example.roman.githubapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roman.githubapp.BaseApplication;

/**
 * Created by roman on 29/07/2017.
 */

public abstract class BaseFragment extends Fragment {


    public BaseApplication app() {
        return BaseApplication.getApp();
    }

    protected abstract int getFragmentResource();

    protected abstract void initFragmentViews(View view);

    protected abstract void initListeners();

    protected abstract void initAdapters();

    protected void getBundleData(Bundle bundle){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentResource(), null);
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        getBundleData(bundle);
        initFragmentViews(view);
        initAdapters();
        initListeners();
        return view;
    }
}
