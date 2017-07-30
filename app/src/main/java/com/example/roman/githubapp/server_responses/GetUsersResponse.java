package com.example.roman.githubapp.server_responses;

import com.example.roman.githubapp.server_data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 30/07/2017.
 */

public class GetUsersResponse extends BaseResponse {


    private List<User> userList = new ArrayList<>();


    @Override
    public BaseResponse createResponse(JSONObject jsonObject) {
        return this;
    }

    @Override
    public BaseResponse createResponse(JSONArray jsonArray)  {
        for (int index = 0; index < jsonArray.length(); index++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    String userName = jsonObject.getString("login");
                    String userImage = jsonObject.getString("avatar_url");
                    userList.add(new User(userName, userImage));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
        return this;
    }

    public List<User> getUserList() {
        return userList;
    }
}
