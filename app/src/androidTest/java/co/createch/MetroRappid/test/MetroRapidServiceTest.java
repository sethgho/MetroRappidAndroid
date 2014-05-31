package co.createch.MetroRappid.test;

import android.util.Log;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;

import co.createch.MetroRappid.model.ResponseEnvelope;
import co.createch.MetroRappid.model.StopResponse;
import co.createch.MetroRappid.service.MetroRapidService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public class MetroRapidServiceTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.d("TEST","Did some shit");
    }

    public void testService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.capmetro.org")
                .build();

        MetroRapidService service = restAdapter.create(MetroRapidService.class);

        Callback<ResponseEnvelope> cb = new Callback<ResponseEnvelope>() {
            @Override
            public void success(ResponseEnvelope resEnv, Response response) {
                signal.countDown();// notify the count down latch
                Log.d("TEST", "Success");
                Log.d("TEST", "POOP");
            }

            @Override
            public void failure(RetrofitError error) {`

                signal.countDown();// notify the count down latch
                Log.d("TEST", "Fail!");
                Log.d("TEST", "POOP");
            }
        };
        service.getRealtimeInfo("801", "5867", "xml", 2, "NB", cb);
    }
}
