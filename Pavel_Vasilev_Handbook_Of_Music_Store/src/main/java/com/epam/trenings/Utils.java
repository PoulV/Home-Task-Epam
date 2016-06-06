package com.epam.trenings;

import com.epam.trenings.model.Handbook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Pol on 6/6/2016.
 */
public class Utils {
    public static void printHandbook(String header, Handbook handbookForPrint) {
        System.out.println(header);
        System.out.println(handbookForPrint);
    }

    public static String getReadableTime(Long milliseconds) {
        Date date = new Date(milliseconds);
        DateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public static Long getMilliseconds(String timeString) {
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            date = sdf.parse("1970-01-01 00:" + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
