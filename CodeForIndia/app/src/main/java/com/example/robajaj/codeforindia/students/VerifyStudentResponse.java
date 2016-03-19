package com.example.robajaj.codeforindia.students;

import com.example.robajaj.codeforindia.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by subhgupt on 3/19/16.
 */
public class VerifyStudentResponse  extends ApiResponse {

    public VerifyStudentResponse()
    {
        id = 1;
        name = "";
    }
    @JsonProperty("id")
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    String name;

}
