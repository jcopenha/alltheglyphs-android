package org.typedef.jcopenha.alltheglyphs;

import android.app.Application;
import android.content.Context;

/**
 * Created by jcopenha on 5/8/16.
 */
public class MainApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MainApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }
}
