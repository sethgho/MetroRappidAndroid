package co.createch.MetroRappid;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import co.createch.MetroRappid.data.FileStopRepository;
import co.createch.MetroRappid.data.RouteRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.FileRouteRepository;
import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappid.service.converter.SimpleXMLConverter;
import retrofit.RestAdapter;

/**
 * Created by Caskman on 5/31/2014.
 */
public class MetroRapidApp extends Application {

    public static final String TAG = MetroRapidApp.class.getName();
    private MetroRapidService mService;
    private StopRepository mStopRepostory;
    private RouteRepository mRouteRepository;

    public MetroRapidApp() {

        Log.d(TAG, "Application context constructor");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://www.capmetro.org")
                .setConverter(new SimpleXMLConverter())
                .build();
        mService = adapter.create(MetroRapidService.class);
        mStopRepostory = new FileStopRepository(getApplicationContext());
        mRouteRepository = new FileRouteRepository(getApplicationContext());
    }

    public static MetroRapidApp from(Activity activity)
    {
        return (MetroRapidApp)activity.getApplication();
    }

    public MetroRapidService getCapMetroService() {
        return mService;
    }

    public StopRepository getStopRepository() {
        return mStopRepostory;
    }

    public RouteRepository getRouteRepository() {
        return mRouteRepository;
    }


}
