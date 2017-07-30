package com.example.roman.githubapp.interfaces;

import com.example.roman.githubapp.server_responses.BaseResponse;

/**
 * Created by roman on 30/07/2017.
 */

public interface OnServerRequestListener {

    void onSuccess(BaseResponse baseResponse);

    void onFailure();
}
