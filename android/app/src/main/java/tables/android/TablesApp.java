package tables.android;

import android.app.Application;

import com.parse.Parse;

public class TablesApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nRy0P11TV7YcSE86h0yyXlQW6bkIWSRZeOtnGEOp", "nNSzr2MFlNIoydrFdWrV1uYl3BReFWyOZNvWiqZn");
    }
}
