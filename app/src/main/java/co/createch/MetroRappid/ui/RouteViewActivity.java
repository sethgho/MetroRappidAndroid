package co.createch.MetroRappid.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.List;

import co.createch.MetroRappid.MetroRapidApp;
import co.createch.MetroRappid.data.RouteRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.ResponseEnvelope;
import co.createch.MetroRappid.model.RouteDirection;
import co.createch.MetroRappid.model.RoutePath;
import co.createch.MetroRappid.model.TripInfo;
import co.createch.MetroRappid.model.TripInfoCollection;
import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappidAndroid.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RouteViewActivity extends BaseLocationActivity implements RouteMapFragment.OnStopClickListener {

    public final static String TAG = RouteViewActivity.class.getName();

    public static final String ARG_ROUTE_ID = "ROUTE_ID";
    public static final String ARG_ROUTE_NAME = "ROUTE_NAME";

    private String mRouteId;
    private RouteMapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_route_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mRouteId = extras.getString(ARG_ROUTE_ID);
            final String title = extras.getString(ARG_ROUTE_NAME);
            this.setTitle(title);
        }

    }

    private void loadRoute() {
        final RouteRepository repo = MetroRapidApp.from(this).getRouteRepository();
        final RoutePath path = repo.getShapesForRoute(mRouteId, RouteDirection.North);
        loadPath(path);
        loadStops();
    }

    private void loadStops() {
        final StopRepository repo = MetroRapidApp.from(this).getStopRepository();
        final CapStopCollection stops = repo.getStopsForRoute(mRouteId, RouteDirection.North);
        mMapFragment.setStops(stops);
    }

    private void loadRealtimeInfo(String stopId) {
        MetroRapidService service = MetroRapidApp.from(this).getCapMetroService();
        setProgressBarIndeterminateVisibility(true);
        service.getRealtimeInfo(mRouteId, stopId, "xml", "X", "NB", new Callback<ResponseEnvelope>() {
            @Override
            public void success(ResponseEnvelope responseEnvelope, Response response) {
                if(responseEnvelope != null &&
                        responseEnvelope.body != null &&
                        responseEnvelope.body.response != null)
                {
                    TripInfoCollection trips = responseEnvelope.body.response.stop.service.getTripInfoCollection();
                    mMapFragment.loadTrips(trips);
                }
                setProgressBarIndeterminateVisibility(false);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), String.format("Error: %s", error.getMessage()), Toast.LENGTH_LONG).show();
                setProgressBarIndeterminateVisibility(false);
            }
        });
    }

    private void loadPath(RoutePath path) {
        mMapFragment.setRoutePath(path);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mMapFragment = (RouteMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mMapFragment.setOnStopClickListener(this);
        loadRoute();
    }

    @Override
    public void onConnected(Bundle dataBundle) {
        mMapFragment.setLocation(mLocationClient.getLastLocation());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        loadRealtimeInfo("5867");
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStopClicked(String stopId) {
        loadRealtimeInfo(stopId);
    }
}
