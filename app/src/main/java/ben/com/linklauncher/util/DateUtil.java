package ben.com.linklauncher.util;

import java.util.Calendar;

public class DateUtil {

    public static String getCurrentDate() {
        return Calendar.getInstance().getTime().toString();
    }
}
