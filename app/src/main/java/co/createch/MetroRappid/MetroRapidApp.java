package co.createch.MetroRappid;

import android.app.Application;
import android.util.Log;

import co.createch.MetroRappid.model.ResponseEnvelope;
import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappid.service.converter.SimpleXMLConverter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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


        Callback<ResponseEnvelope> cb = new Callback<ResponseEnvelope>() {
            @Override
            public void success(ResponseEnvelope resEnv, Response response) {
                Log.d("TEST", "Success");
                Log.d("TEST", "POOP");
                String str = String.format("trips count = %d", resEnv.body.response.stop.service.trips.size());
                Log.d("TEST", str);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TEST", "Fail!");
                Log.d("TEST", "POOP");
            }
        };

        Log.d("wef", "About to getRealtimeInfo");
        mService.getRealtimeInfo("801", "5867", "xml", 2, "NB", cb);

    };


    public MetroRapidService getCapMetroService()
    {
        return mService;
    }
}
