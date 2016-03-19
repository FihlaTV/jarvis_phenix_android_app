package com.example.robajaj.codeforindia.students;

import com.example.robajaj.codeforindia.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by subhgupt on 3/19/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetStudentCountResponse extends ApiResponse {

    public GetStudentCountResponse()
    {

    }
    @JsonProperty("count")
    Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public long getTimeStamp() {

        return timeStamp;
    }

    public Date getDate() {

        Date date= new Date(timeStamp);
        return date;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @JsonProperty("timestamp")
    long timeStamp;
}
