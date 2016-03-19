package com.example.robajaj.codeforindia.response;

public interface IApiResponse {

    public void setHttpStatusCode(int code);

    public int getHttpStatusCode();

    public String getHttpStatusText();

    public void setHttpStatusText(String text);

    public boolean isOk();
}
