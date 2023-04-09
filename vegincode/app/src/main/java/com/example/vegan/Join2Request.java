package com.example.vegan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Join2Request extends StringRequest {
    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://ec2-3-22-169-59.us-east-2.compute.amazonaws.com/register.php";
    private Map<String, String> parameters;


    public Join2Request(String userID, String mName, String mDate, String mType, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("email", userID);
        parameters.put("name", mName);
        parameters.put("start_date", mDate);
        parameters.put("type", mType);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
