package com.example.roman.githubapp.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.roman.githubapp.R;
import com.example.roman.githubapp.adapters.UsersAdapter;
import com.example.roman.githubapp.server_data.User;
import com.example.roman.githubapp.server_responses.GetUsersResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 29/07/2017.
 */

public class HomeFragment extends BaseFragment {


    public static final String USERS_EXTRA = "usersExtra";

    private RecyclerView rvUsersList;
    private List<User> userList = new ArrayList<>();

    @Override
    protected int getFragmentResource() {
        return R.layout.fragment_home;
    }


    @Override
    protected void getBundleData(Bundle bundle) {
        if (bundle.containsKey(USERS_EXTRA)) {
            GetUsersResponse response = (GetUsersResponse)bundle.getSerializable(USERS_EXTRA);
            userList = response.getUserList();
        }
    }

    @Override
    protected void initFragmentViews(View view) {
        rvUsersList = (RecyclerView)view.findViewById(R.id.rvUsersList);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {
        UsersAdapter usersAdapter = new UsersAdapter(app(), userList);
        rvUsersList.setLayoutManager(new LinearLayoutManager(app()));
        rvUsersList.setAdapter(usersAdapter);
    }
}
