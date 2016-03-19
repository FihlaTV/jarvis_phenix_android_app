package com.example.robajaj.codeforindia.students;

import android.content.Context;
import android.util.Log;

import com.example.robajaj.codeforindia.request.AbstractApiServerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.IOException;

/**
 * Created by subhgupt on 3/19/16.
 */
public class GetStudentCountRequest extends AbstractApiServerRequest<GetStudentCountResponse> {

        private final String TAG = "GetStudentCountRequest";

        private int mCourseId;
        public GetStudentCountRequest( Context context, int  courseId)
        {
            super(context);
            mCourseId = courseId;
        }

        public GetStudentCountResponse execute() throws IOException
        {
            String path ="/students?course_id=" + mCourseId;
            String url = constructUrl(path);
            HttpGet request = new HttpGet(url);
            authorizeHttpMethod(request);
            GetStudentCountResponse resp = processHttpRequest(request);
            return resp;

        }


        protected GetStudentCountResponse parseResponse(HttpResponse httpResponse) throws IOException
        {
            GetStudentCountResponse response = new GetStudentCountResponse();
            if(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)
            {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String body = getBodyString();
                    response = mapper.readValue(body, GetStudentCountResponse.class);
                }catch(Exception e)
                {
                    Log.e(TAG, e.getMessage());
                }
            }
            return  response;
        }

    }
