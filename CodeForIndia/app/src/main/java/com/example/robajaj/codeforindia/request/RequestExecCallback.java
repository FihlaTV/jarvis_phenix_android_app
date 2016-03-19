package com.example.robajaj.codeforindia.request;


import com.example.robajaj.codeforindia.response.ApiResponse;

public abstract class RequestExecCallback<U extends ApiResponse> {

    public abstract void onPostRequestExec(U result);

    public void onPreRequestExec()
    {

    }
}
