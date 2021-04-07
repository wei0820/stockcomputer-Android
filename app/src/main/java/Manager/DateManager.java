package Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {

    public static int dateDiff(String startDate,  String endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(stringToDate(startDate));
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(stringToDate(endDate));
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }


    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        return  calendar.get(Calendar.YEAR);


    }

    public static int getMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);


    }
    public static int getDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);


    }

    public static Date stringToDate(String stringDate){
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

             date = sdf.parse(stringDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
