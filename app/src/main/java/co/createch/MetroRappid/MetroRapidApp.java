package co.createch.MetroRappid;

import android.app.Application;
import android.util.Log;

import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappid.service.converter.SimpleXMLConverter;
import retrofit.RestAdapter;

/**
 * Created by Caskman on 5/31/2014.
 */
public class MetroRapidApp extends Application {

    public static final String TAG = MetroRapidApp.class.getName();
    private MetroRapidService mService;

    public MetroRapidApp() {

        Log.d(TAG,"Application context constructor");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://www.capmetro.org")
                .setConverter(new SimpleXMLConverter())
                .build();
        mService = adapter.create(MetroRapidService.class);
    }


    public MetroRapidService getCapMetroService()
    {
        return mService;
    }
}
