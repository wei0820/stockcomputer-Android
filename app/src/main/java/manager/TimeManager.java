package manager;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class TimeManager {

    public static  String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.TAIWAN);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("yyyy-MM-dd", cal).toString();
        return date;
    }
}
