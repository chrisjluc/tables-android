package tables.android.utils;

import android.os.Build;

public class SdkUtils {
    public static boolean supportsLollipop(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
