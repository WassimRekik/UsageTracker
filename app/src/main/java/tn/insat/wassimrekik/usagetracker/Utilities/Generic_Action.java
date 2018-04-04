package tn.insat.wassimrekik.usagetracker.Utilities;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wassimrekik on 04/04/2018.
 */

public class Generic_Action {
    public static boolean check_current_month(Date givenDate){
        //Create 2 intances of calendar
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        //set the given date in one of the instance and current date in another
        cal1.setTime(givenDate);
        cal2.setTime(new Date());

        //now compare the dates using functions
        if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
            if(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                // the date falls in current month
                return true;
            }
        }
        return false;

    }
    public static String convertToMinSecDuration(String duration){
        Integer durat = Integer.parseInt(duration);
        Integer min = durat/60;
        Integer sec = durat % 60;
        if (durat == 0){
            return "0sec";
        }else{
            if (sec < 10){
                return min + ":0" + sec ;
            }else {
                return min + ":" + sec;
            }
        }
    }
}
