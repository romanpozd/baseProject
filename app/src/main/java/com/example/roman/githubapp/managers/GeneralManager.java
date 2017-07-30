package com.example.roman.githubapp.managers;

import android.os.Bundle;

import com.example.roman.githubapp.BaseApplication;
import com.example.roman.githubapp.fragments.HomeFragment;
import com.example.roman.githubapp.interfaces.OnServerRequestListener;
import com.example.roman.githubapp.server_requests.GetUsersRequest;
import com.example.roman.githubapp.server_responses.BaseResponse;
import com.example.roman.githubapp.server_responses.GetUsersResponse;

/**
 * Created by roman on 29/07/2017.
 */

public class GeneralManager extends BaseManager {

    public static GeneralManager generalManager;

    public GeneralManager(BaseApplication app) {
        super(app);
    }

    @Override
    public void initManager() {

    }

    public static GeneralManager getInstance(BaseApplication app) {
        if (generalManager == null) {
            generalManager = new GeneralManager(app);
        }
        return generalManager;
    }


    public void pushHomeFragment(GetUsersResponse getUsersResponse) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(HomeFragment.USERS_EXTRA, getUsersResponse);
        homeFragment.setArguments(bundle);
        app().getCurrentActivity().pushFragment(homeFragment);
    }

    public void sendGetUsersRequest() {
        app().getRequestManager().addRequestToQueue(getUsersRequest());
    }

    private GetUsersRequest getUsersRequest() {
        return new GetUsersRequest(new OnServerRequestListener() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                GetUsersResponse getUsersResponse = (GetUsersResponse)baseResponse;
                pushHomeFragment(getUsersResponse);
            }

            @Override
            public void onFailure() {

            }
        });
    }

}
