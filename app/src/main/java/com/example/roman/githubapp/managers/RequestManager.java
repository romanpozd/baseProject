package com.example.roman.githubapp.managers;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.roman.githubapp.BaseApplication;
import com.example.roman.githubapp.server_requests.BaseServerRequest;

/**
 * Created by roman on 29/07/2017.
 */

public class RequestManager extends BaseManager {

    private static RequestManager requestManagerInstance;
    private static final String BASE_HOST_URL = "https://api.github.com/";

    private RequestQueue requestQueue;

    public RequestManager(BaseApplication baseApplication) {
        super(baseApplication);
    }


    public static RequestManager getRequestManagerInstance(BaseApplication app) {
        if (requestManagerInstance == null) {
            requestManagerInstance = new RequestManager(app);
        }
        return requestManagerInstance;
    }


    private synchronized RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(app());
        }
        return requestQueue;
    }


    public void addRequestToQueue(BaseServerRequest baseServerRequest) {
        getRequestQueue().add(baseServerRequest);
    }

    public String getBaseHostUrl() {
        return BASE_HOST_URL;
    }

}
