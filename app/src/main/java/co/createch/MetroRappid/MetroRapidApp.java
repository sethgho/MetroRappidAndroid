package co.createch.MetroRappid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Caskman on 5/31/2014.
 */
public class MetroRapidApp extends Application {

    public static final String TAG = MetroRapidApp.class.getName();

    public MetroRapidApp() {

        Log.d(TAG,"Application context constructor");

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
