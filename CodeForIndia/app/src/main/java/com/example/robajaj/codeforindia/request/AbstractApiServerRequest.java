package com.example.robajaj.codeforindia.request;

import android.content.Context;

import com.example.robajaj.codeforindia.ApiServerConfig;
import com.example.robajaj.codeforindia.response.IApiResponse;

import org.apache.http.client.methods.HttpRequestBase;

public abstract class AbstractApiServerRequest<T extends IApiResponse> extends BaseApiServerRequest<T> {

    private final String SERVER_BASE_URL = ApiServerConfig.ServerUrl;
    private final String TAG = "AbstractApiServerRequest";


    public AbstractApiServerRequest(Context context1) {
        super(context1);
    }

    protected String constructUrl(String path)
    {
        String url = SERVER_BASE_URL + path;
        return url;

    }

    protected void authorizeHttpMethod(HttpRequestBase reqBase)
    {
//        reqBase.addHeader(
//                new BasicHeader(
//                        "Authorization", "Bearer " + token)
//        );
    }

}
