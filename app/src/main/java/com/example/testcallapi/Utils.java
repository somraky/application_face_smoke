package com.example.testcallapi;

import android.util.Log;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    //Medthod for convert timestamp to Date and Time
    public static String TimeFormat(String timestamp){
        PrettyTime p = new PrettyTime(new Locale("th", "TH"));
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                    Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(timestamp);
            isTime = p.format(date);
            Log.d("timestamp",date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isTime;
    }

    public static String DateFormat(String timestamp){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy, HH:mm:ss", new Locale("th", "TH"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(timestamp);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = timestamp;
        }

        return newDate;
    }
}
