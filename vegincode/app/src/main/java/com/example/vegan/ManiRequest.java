package com.example.vegan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ManiRequest extends StringRequest {
    final static private String URL = "http://ec2-3-22-169-59.us-east-2.compute.amazonaws.com/calendar_manipulation.php";
    //날짜 클릭하면 텍스트 반환하도록
    private Map<String, String> map;

    public ManiRequest(String num, String email, String date, String text, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("num",num);
        map.put("email", email);
        map.put("date", date);
        map.put("text", text);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}