package com.example.vegan;

import android.os.AsyncTask;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MarkDecorator extends AsyncTask<Void, Void, List<CalendarDay>> {
    @Override
    protected List<CalendarDay> doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0); //현재 달부터
        ArrayList<CalendarDay> dates = new ArrayList<>();
        //dates가 비어있으면 아무것도 출력 x
        if (dates.isEmpty()) {

        } else {

        }

//        for (int i = 0; i < 30; i++) {
//            CalendarDay day = CalendarDay.from(calendar);
//            dates.add(day);
//            Calendar.add(Calendar.DATE, 0);
//        }
        return dates;
    }
}
