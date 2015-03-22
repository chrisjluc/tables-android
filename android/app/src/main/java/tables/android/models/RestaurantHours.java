package tables.android.models;

import android.util.SparseArray;

import java.util.Calendar;
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

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        // + 5 because offset by 2 days and add a week to use modulus
        int day = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        HashMap<String, String> startAndEndTimes = mHours.get(day);
        return startAndEndTimes.get(OPEN) + " - " + startAndEndTimes.get(CLOSE);
    }
}
