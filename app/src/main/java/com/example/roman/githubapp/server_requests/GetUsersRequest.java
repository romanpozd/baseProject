package com.example.roman.githubapp.server_requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.roman.githubapp.interfaces.OnServerRequestListener;
import com.example.roman.githubapp.server_responses.BaseResponse;
import com.example.roman.githubapp.server_responses.GetUsersResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 30/07/2017.
 */

public class GetUsersRequest extends BaseServerRequest {

    private static final String METHOD_NAME = "users";

    public GetUsersRequest(OnServerRequestListener onServerRequestListener) {
        super(Method.GET, METHOD_NAME, onServerRequestListener);
    }

    @Override
    protected BaseResponse buildResponse(JSONObject jsonObject) {
        return new GetUsersResponse().createResponse(jsonObject);
    }

    @Override
    protected BaseResponse buildResponse(JSONArray jsonArray) {
        return new GetUsersResponse().createResponse(jsonArray);
    }

    @Override
    protected HashMap<String, String> getRequestParams() {
        HashMap<String, String> params = new HashMap<>();
        return params;
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
