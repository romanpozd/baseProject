package com.example.roman.githubapp.server_requests;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.roman.githubapp.BaseApplication;
import com.example.roman.githubapp.interfaces.OnServerRequestListener;
import com.example.roman.githubapp.server_responses.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 29/07/2017.
 */

public abstract class BaseServerRequest extends Request implements Response.ErrorListener{

    private OnServerRequestListener onServerRequestListener;
    private HashMap<String, String> params;


    protected abstract BaseResponse buildResponse(JSONObject jsonObject);

    protected abstract BaseResponse buildResponse(JSONArray jsonArray);

    protected abstract HashMap<String, String> getRequestParams();

    public BaseServerRequest(int method, String url, OnServerRequestListener onServerRequestListener) {
        super(method, getBaseHostUrl(url), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                boolean flag = true;
            }
        });
        this.onServerRequestListener = onServerRequestListener;
        this.params = getRequestParams();
    }


    private static String getBaseHostUrl(String url) {
        return BaseApplication.getApp().getRequestManager().getBaseHostUrl() + url;
    }


    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                try {
                        return Response.success(new JSONObject(jsonString),
                                HttpHeaderParser.parseCacheHeaders(response));
                    } catch (JSONException e) {
                        return Response.success(new JSONArray(jsonString),
                                HttpHeaderParser.parseCacheHeaders(response));
                }

            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }


    @Override
    public Response.ErrorListener getErrorListener() {
        return super.getErrorListener();
    }


    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        notifyServerRequestListenerError();
        return super.parseNetworkError(volleyError);
    }


    private void notifyServerRequestListenerError() {
        if (onServerRequestListener != null) {
            onServerRequestListener.onFailure();
        }
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected void deliverResponse(Object response) {
        new Thread(new ResponseParse(response, this)).run();
    }


    private class ResponseParse implements Runnable {

        private static final int RESPONSE_PARSE_FINISHED = 10001;

        private Object response;
        private BaseServerRequest baseServerRequest;
        private Handler responseHandler;

        public ResponseParse(Object response, BaseServerRequest baseServerRequest) {
            this.response = response;
            this.baseServerRequest = baseServerRequest;
            initResponseHandler();
        }

        @Override
        public void run() {
            BaseResponse baseResponse;
            if (response instanceof JSONArray) {
                baseResponse = baseServerRequest.buildResponse((JSONArray) response);
            } else {
                baseResponse = baseServerRequest.buildResponse((JSONObject) response);
            }
            Message responseMsg = responseHandler.obtainMessage(RESPONSE_PARSE_FINISHED, baseResponse);
            responseMsg.sendToTarget();
        }


        private void initResponseHandler() {
            responseHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case RESPONSE_PARSE_FINISHED:
                            BaseResponse baseResponse = (BaseResponse) msg.obj;
                            notifyServerRequestListeners(baseResponse);
                            break;
                    }
                }
            };
        }

        private void notifyServerRequestListeners(BaseResponse baseResponse) {
            if (onServerRequestListener != null) {
                onServerRequestListener.onSuccess(baseResponse);
            }
        }
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
