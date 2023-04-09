package com.example.vegan;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {
    //요소 선언해놓기
    EditText mID, mPassword;
    Button mLoginButton, mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //id이용해서 지정.
        mID = (EditText) findViewById(R.id.iD);
        mPassword = (EditText) findViewById(R.id.password);

        mLoginButton = (Button) findViewById(R.id.loginButton); // sign in button
        mSignUpButton = (Button) findViewById(R.id.signupButton); // sign up button

        //회원가입하기 버튼 클릭
        mSignUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
            startActivity(intent);
        });
    }

    //로그인 버튼 클릭
    public void onClick(View v){
        String userID = mID.getText().toString();
        String userPassword = mPassword.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) { //로그인이 가능하다
                        String userID = jsonResponse.getString("email");
                        String userPassword = jsonResponse.getString("pw");
                        Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();

                        //로그인 정보 sharedpreference에 저장.
                        //test 이름 파일 과 기본모드를 설정
                        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정
                        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                        editor.putString("email",userID); // key,value 형식으로 저장
                        editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.


                        // 로그인 하면서 사용자 정보 넘기기
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("userPassword", userPassword);
                        startActivity(intent);

                        finish();

                        //자동 로그인을 위한 정보 저장 (테스트 할때는 주석처리하는 것을 추천)
                        SaveSharedPreferences.setUserEmail(LoginActivity.this, userID);
                        SaveSharedPreferences.setUserPwd(LoginActivity.this, userPassword);

                    } else {
                        Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }
}