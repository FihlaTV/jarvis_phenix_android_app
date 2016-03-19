package com.example.robajaj.codeforindia.students;

import android.content.Context;
import android.util.Log;

import com.example.robajaj.codeforindia.request.AbstractApiServerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by subhgupt on 3/19/16.
 */
public class VerifyStudentRequest extends AbstractApiServerRequest<VerifyStudentResponse> {

        private final String TAG = "VerifyStudentRequest";

        private int mCourseId;
        private String mIdentity;

        public VerifyStudentRequest(Context context, int courseId, String identity)
        {
            super(context);
            mCourseId = courseId;
            mIdentity = identity;
        }

        public VerifyStudentResponse execute() throws IOException
        {
            String path ="/students/verify?course_id=" + mCourseId + "&identity="+mIdentity;
            String url = constructUrl(path);
            HttpGet request = new HttpGet(url);
            authorizeHttpMethod(request);
            VerifyStudentResponse resp = processHttpRequest(request);
            return resp;

        }


        protected VerifyStudentResponse parseResponse(HttpResponse httpResponse) throws IOException
        {
            VerifyStudentResponse response = new VerifyStudentResponse();
            if(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)
            {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String body = getBodyString();
                    response = mapper.readValue(body, VerifyStudentResponse.class);
                }catch(Exception e)
                {
                    Log.e(TAG, e.getMessage());
                }
            }
            return  response;
        }

}
