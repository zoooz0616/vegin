package com.example.vegan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

public class Join2Activity extends AppCompatActivity {
    static final int DATE_DIALOG_ID = 0;
    String mName, mType, userID, mdate;
    EditText name;
    private int mYear=0, mMonth=0, mDay=0; //날짜 0으로 초기화.
    Button mJoinButton;
    DatePicker datePicker;

    int currentYear=0, currentMonth=0, currentDay=0;

    int ddayValue =0;//디데이 값.

    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        Intent intent = getIntent();//JoinActivity로부터 받음.
        userID = intent.getExtras().getString("userID");
        name = findViewById(R.id.name);//닉네임

        mJoinButton=findViewById(R.id.joinButton);//가입하기 버튼


        //선택한 날짜를 현재 날짜로 초기화
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH)+1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mdate = mYear+"-"+mMonth+"-"+mDay;//날짜 합치기(db에 보내려고)

        datePicker=findViewById(R.id.datePicker);

        //현재 날짜 값 저장.
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH)+1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        //비건 타입 선택
        Spinner spinner = (Spinner)findViewById(R.id.vegan_type);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType=spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    public void mOnClick(View v){
        mName= name.getText().toString();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateListener, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        mYear = datePicker.getYear();
        mMonth = datePicker.getMonth()+1;
        mDay = datePicker.getDayOfMonth();
        mdate = mYear+"-"+mMonth+"-"+mDay;

        //디데이
        ddayValue = ddayResult_int(mYear, mMonth, mDay)+1;

        // 회원가입 절차 시작
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    //mdate = jsonResponse.getString("start_date");

                    if (success){ // 회원가입이 가능한다면
                        Toast.makeText(getApplicationContext(), "가입 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), mdate, Toast.LENGTH_SHORT).show();


                        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정
                        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                        editor.putString("name",mName);
                        editor.putString("date",mdate);
                        editor.putInt("dday",ddayValue);
                        editor.putString("type",mType);
                        // key,value 형식으로 저장
                        editor.commit();

                        //로그인 화면으로 이동.
                        Intent intent = new Intent(Join2Activity.this, LoginActivity.class );
                        intent.putExtra("userID", userID);
                        startActivity(intent);
                        finish();//액티비티를 종료시킴(회원등록 창을 닫음)

                    } else{// 회원가입이 안된다면
                        Toast.makeText(getApplicationContext(), "가입 실패하였습니다. ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        Join2Request join2Request = new Join2Request(userID, mName, mdate, mType, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Join2Activity.this);
        queue.add(join2Request);

    }

    DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    mYear = datePicker.getYear();
                    mMonth = datePicker.getMonth();
                    mDay = datePicker.getDayOfMonth();
                    mdate = mYear+"-"+mMonth+"-"+mDay;

                }
            };

    //디데이 값 계산한 값 결과값 출력
    public int ddayResult_int(int dateEndY, int dateEndM, int dateEndD) {
        int result = 0;

        result = onCalculatorDate(dateEndY, dateEndM, dateEndD);

        return result;
    }

    //디데이 값 계산
    public int onCalculatorDate (int dateEndY, int dateEndM, int dateEndD) {
        try {
            Calendar today = Calendar.getInstance(); //현재 오늘 날짜
            Calendar dday = Calendar.getInstance();

            //시작일, 종료일 데이터 저장
            Calendar calendar = Calendar.getInstance();
            int cyear = calendar.get(Calendar.YEAR);
            int cmonth = (calendar.get(Calendar.MONTH) + 1);
            int cday = calendar.get(Calendar.DAY_OF_MONTH);

            today.set(cyear, cmonth, cday);
            dday.set(dateEndY, dateEndM, dateEndD);// D-day의 날짜를 입력합니다.

            long day = dday.getTimeInMillis() / 86400000;
            // 각각 날의 시간 값을 얻어온 다음
            //( 1일의 값(86400000 = 24시간 * 60분 * 60초 * 1000(1초값) ) )

            long tday = today.getTimeInMillis() / 86400000;
            long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            return (int) count; // 날짜는 하루 + 시켜줘야합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}