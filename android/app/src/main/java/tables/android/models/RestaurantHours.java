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

    public boolean isOpen() {
        // Since open and close time will be at epoch when parsed without a date
        // normalize to epoch as well
        Calendar c = Calendar.getInstance();
        c.set(1970, 0, 1);
        Date currentTime = c.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        HashMap<String, String> startAndEndTimes = mHours.get(getCurrentDay());
        try {
            Date openTime = sdf.parse(startAndEndTimes.get(OPEN));
            Date closeTime = sdf.parse(startAndEndTimes.get(CLOSE));

            if (closeTime.before(openTime)){
                Calendar temp = Calendar.getInstance();
                temp.setTime(closeTime);
                temp.add(Calendar.DATE, 1);
                closeTime = temp.getTime();
            }
            Calendar temp = Calendar.getInstance();
            temp.setTime(currentTime);
            temp.add(Calendar.DATE, 1);
            Date currentTimePlusDay = temp.getTime();

            return (currentTime.after(openTime) && currentTime.before(closeTime)) ||
                    (currentTimePlusDay.after(openTime) && currentTimePlusDay.before(closeTime));
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
