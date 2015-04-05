package tables.android.models;

import android.util.SparseArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RestaurantHours {

    public static final String OPEN = "Starting";
    public static final String CLOSE = "Ending";

    private SparseArray<HashMap<String, String>> mHours;

    public RestaurantHours(Object o) {
        mHours = new SparseArray<>();
        HashMap<String, HashMap<String, String>> hours = (HashMap<String, HashMap<String, String>>) o;
        for (int i = 0; i < 7; i++) {
            HashMap<String, String> hoursInfo = hours.get("" + i);
            mHours.put(i, hoursInfo);
        }
    }

    private int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        // + 5 because offset by 2 days and add a week to use modulus
        return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
    }

    //TODO need to return a string of open until 3AM for example day before is open until 2am and it's 1 am now it should catch that
    public boolean isOpen() {
        // Since open and close time will be at epoch when parsed without a date
        // normalize to epoch as well
        Calendar c = Calendar.getInstance();
        c.set(1970, 0, 1);
        Date currentTime = c.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        HashMap<String, String> startAndEndTimes = mHours.get(getCurrentDay());
        HashMap<String, String> yesterdayStartAndEndTimes = mHours.get(getCurrentDay() - 1);
        try {
            Date openTime = sdf.parse(startAndEndTimes.get(OPEN));
            Date closeTime = sdf.parse(startAndEndTimes.get(CLOSE));

            // Handles when restaurant time wraps to another day
            if (closeTime.before(openTime)) {
                Calendar temp = Calendar.getInstance();
                temp.setTime(closeTime);
                temp.add(Calendar.DATE, 1);
                closeTime = temp.getTime();
            }

            // Looks back the day before and see if the time crosses into today, and if it's still open.
            Date yesterdayOpenTime = sdf.parse(yesterdayStartAndEndTimes.get(OPEN));
            Date yesterdayCloseTime = sdf.parse(yesterdayStartAndEndTimes.get(CLOSE));
            Date currentTimePlusDay = null;

            boolean checkPreviousTimes = false;
            if (yesterdayCloseTime.before(yesterdayOpenTime)) {
                checkPreviousTimes = true;
                Calendar temp = Calendar.getInstance();
                temp.setTime(yesterdayCloseTime);
                temp.add(Calendar.DATE, 1);
                yesterdayCloseTime = temp.getTime();

                // When the previous day end time wraps to the next day
                temp = Calendar.getInstance();
                temp.setTime(currentTime);
                temp.add(Calendar.DATE, 1);
                currentTimePlusDay = temp.getTime();
            }

            return (currentTime.after(openTime) && currentTime.before(closeTime)) ||
                    (checkPreviousTimes && currentTimePlusDay.after(yesterdayOpenTime) && currentTimePlusDay.before(yesterdayCloseTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        HashMap<String, String> startAndEndTimes = mHours.get(getCurrentDay());
        return startAndEndTimes.get(OPEN) + " - " + startAndEndTimes.get(CLOSE);
    }
}
