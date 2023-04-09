package com.example.vegan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {
    //요소 선언
    Button mContinueButton;
    EditText mID, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //id이용해서 값 가져오기
        mID = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mContinueButton = (Button) findViewById(R.id.continueButton);
    }

    //continue버튼 클릭시
    public void onClick(View v){
        // 현재 입력된 정보를 string으로 가져오기
        String userID = mID.getText().toString();
        String userPassword = mPassword.getText().toString();

        // 회원가입 절차 시작
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){ // 회원가입이 가능한다면
                        //String userID = jsonResponse.getString("email");
                        //String userPassword = jsonResponse.getString("pw");
                        Toast.makeText(getApplicationContext(), "가입 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                        //password는 두개 같은지 확인하

                        //Join2 액티비티로 이동.
                        Intent intent = new Intent(JoinActivity.this, Join2Activity.class );
                        intent.putExtra("userID", userID);
                        startActivity(intent);
                        finish();//액티비티를 종료시킴(회원등록 창을 닫음)

                    } else{// 회원가입이 안된다면
                        Toast.makeText(getApplicationContext(), "중복된 ID입니다. ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        JoinRequest joinRequest = new JoinRequest(userID, userPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
        queue.add(joinRequest);
    }
}