package com.example.roman.githubapp.server_responses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by roman on 30/07/2017.
 */

public abstract class BaseResponse implements Serializable{

    public abstract BaseResponse createResponse(JSONObject jsonObject);

    public abstract BaseResponse createResponse(JSONArray jsonArray) throws JSONException;
}
