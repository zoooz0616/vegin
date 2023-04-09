package com.example.vegan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent;
            //저장된 사용자 정보 없으면 loginactivity로 이동
            if(SaveSharedPreferences.getUserEmail(IntroActivity.this).length() == 0 || SaveSharedPreferences.getUserPwd(IntroActivity.this).length() == 0) {
                intent = new Intent(IntroActivity.this, LoginActivity.class);
            } else { //저장된 사용자 정보가 있다면 메인 화면으로 이동
                intent = new Intent(IntroActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish(); // Activity 화면 제거
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        /*splash 시작. 인트로 화면 후 LoginActivity로 화면 전환*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);//3초
    }
}