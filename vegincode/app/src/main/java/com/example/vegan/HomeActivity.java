package com.example.vegan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends Fragment implements OnDateSelectedListener {
    EditText memo;
    Button savebutton, updatebutton, deletebutton;
    LinearLayout layout2;
    String email;
    String result;//날짜 스트링.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.activity_home, container, false);

        memo = v.findViewById(R.id.memo);
        savebutton=v.findViewById(R.id.savebutton);
        updatebutton=v.findViewById(R.id.updatebutton);
        deletebutton=v.findViewById(R.id.deletebutton);
        layout2=v.findViewById(R.id.layout2);

        //loginactivity로부터 email 값 받기.
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","aaa");

        MaterialCalendarView materialCalendarView;
        materialCalendarView = v.findViewById(R.id.calendarView);

        //오늘 날짜 클릭
        materialCalendarView.setSelectedDate(CalendarDay.today());
        onDateSelected(materialCalendarView, CalendarDay.today(),true);

        materialCalendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator()
        );

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(1990, 1, 1)) //달력의 시작
                .setMaximumDate(CalendarDay.from(2050, 12, 31)) //달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //날짜 클릭 시 이벤트, 메모 작성 유효성 검사
        materialCalendarView.setOnDateChangedListener(this);

        //메모 작성하고 저장 버튼 누를
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = memo.getText().toString(); //일기 내에서

                //volley 라이브러리
                //저장 눌렀을때 디비로 보내고,

                //일정 추가
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) { //일정 입력 가능하면 저장 후 버튼 변경
                                //버튼 기능 구현
                                Toast.makeText(getActivity().getApplicationContext(), "저장 됐습니다.", Toast.LENGTH_SHORT).show();
                                savebutton.setVisibility(View.GONE);
                                layout2.setVisibility(View.VISIBLE);
                                //memo.setText(null);

                            } else {//일정 입력이 안된다면
                                Toast.makeText(getActivity().getApplicationContext(), "입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };


                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ManiRequest maniRequest = new ManiRequest("1", email, result, text, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(maniRequest);

            }
        });

        //메모 작성하고 수정 버튼 누를 때
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = memo.getText().toString();//수정한 내용.

                //volley 라이브러리
                //수정 누르면 이메일과 데이트 교집합 내용의 text 변경.

                //수정.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) { //텍스트 내용으로 수정.
                                //버튼 기능 구현
                              Toast.makeText(getActivity().getApplicationContext(), "수정 됐습니다.", Toast.LENGTH_SHORT).show();
                            } else {// 수정 안된다면
                                Toast.makeText(getActivity().getApplicationContext(), "수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ManiRequest maniRequest = new ManiRequest("2", email, result, text, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(maniRequest);

            }
        });

        //메모 삭제 버튼
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = memo.getText().toString(); //일기 내에서

                //일정 삭제
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) { //삭제
                                //버튼 기능 구현
                                memo.setText(null);
                                Toast.makeText(getActivity().getApplicationContext(), "삭제 됐습니다.", Toast.LENGTH_SHORT).show();
                                layout2.setVisibility(View.GONE);
                                savebutton.setVisibility(View.VISIBLE);
                            } else {//삭제
                                Toast.makeText(getActivity().getApplicationContext(), "삭제할 수 없습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ManiRequest maniRequest = new ManiRequest("3", email, result, text, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(maniRequest);

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //날짜를 년도-월-일 string으로 변경
        Date time = date.getDate();//날짜를 년도-월-일 string으로 변경
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        result = df.format(time);
        //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

        //memo값 비었을 때(success, true)=>저장 버튼 생성,   안 비어있으면 실패(false)=>수정, 삭제 버튼 생성.
        Response.Listener<String> responseListener = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                String tt= jsonResponse.getString("text");
                //Toast.makeText(getApplicationContext(), tt, Toast.LENGTH_SHORT).show();

                if (success) { // memo 값 새로 작성 가
                    memo.setText(null);
                    savebutton.setVisibility(View.VISIBLE); //저장 버튼 표시
                    layout2.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext(), "입력 가능합니다.", Toast.LENGTH_SHORT).show();

                } else {// 이미 있음
                    memo.setText(tt);
                    savebutton.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };


        // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        CalendarRequest calendarRequest = new CalendarRequest(email, result, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(calendarRequest);
    }
}