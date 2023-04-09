package com.example.vegan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserActivity extends Fragment {
    String email, name, date, type;
    int dday;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v=inflater.inflate(R.layout.activity_user, container, false);

        TextView memail = v.findViewById(R.id.email);
        TextView mname = v.findViewById(R.id.name);
        TextView mtype = v.findViewById(R.id.type);
        TextView mdate = v.findViewById(R.id.date);
        TextView mdday = v.findViewById(R.id.dday);

        TextView tree = v.findViewById(R.id.tree);
        TextView grain = v.findViewById(R.id.grain);
        TextView co2 = v.findViewById(R.id.co2);
        TextView water = v.findViewById(R.id.water);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","aaa");
        memail.setText(email);

        name = sharedPreferences.getString("name","aaa");
        mname.setText(name);

        type = sharedPreferences.getString("type","비건");
        mtype.setText(type);

        date = sharedPreferences.getString("date","2021-01-01");
        mdate.setText(date+" ~ing");

        dday = sharedPreferences.getInt("dday",1);
        mdday.setText(dday+"일 째 채식 중");
        // key,value 형식으로 저장

        tree.setText(dday*2.8+"m^2");
        grain.setText(dday*20+"kg");
        co2.setText(dday*4.5+"kg");
        water.setText(dday*4164+"L");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}