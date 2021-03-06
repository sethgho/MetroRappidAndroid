package co.createch.MetroRappid.ui;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import co.createch.MetroRappid.MetroRapidApp;
import co.createch.MetroRappid.data.RouteRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RealtimeInfoResponseEnvelope;
import co.createch.MetroRappid.model.RouteDirection;
import co.createch.MetroRappid.model.RoutePath;
import co.createch.MetroRappid.model.StopService;
import co.createch.MetroRappid.model.TripInfoCollection;
import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappidAndroid.R;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RouteViewActivity extends BaseLocationActivity implements RouteMapFragment.OnStopClickListener, ActionBar.OnNavigationListener {

    public final static String TAG = RouteViewActivity.class.getName();

    public static final String ARG_ROUTE_ID = "ROUTE_ID";
    private String[] mRouteValues;

    private Activity activity = this;

    private String mRouteId;
    private RouteDirection mRouteDirection;
    private RouteMapFragment mMapFragment;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_route_view);
        Bundle extras = getIntent().getExtras();
        getActionBar().setDisplayShowTitleEnabled(false);
        if (extras != null) {
            mRouteId = extras.getString(ARG_ROUTE_ID);
        }
        mRouteId = "801";
        mRouteDirection = RouteDirection.North;
        setupActionBar();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        mRouteValues = getResources().getStringArray(R.array.routes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mRouteValues);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(adapter, this);
    }

    private void loadRoute() {
        final RouteRepository pathRepo = MetroRapidApp.from(this).getRouteRepository();
        final StopRepository stopRepo = MetroRapidApp.from(this).getStopRepository();

        final RoutePath path = pathRepo.getShapesForRoute(mRouteId, mRouteDirection);
        final CapStopCollection stops = stopRepo.getStopsForRoute(mRouteId, mRouteDirection);
        mMapFragment.loadRouteData(path, stops);
    }

    private void loadRealtimeInfo(String stopId) {
        MetroRapidService service = MetroRapidApp.from(this).getCapMetroService();
        setProgressBarIndeterminateVisibility(true);
        service.getRealtimeInfo(mRouteId, stopId, "xml", "X", "NB", new Callback<RealtimeInfoResponseEnvelope>() {
            @Override
            public void success(RealtimeInfoResponseEnvelope responseEnvelope, Response response) {
                if (responseEnvelope != null &&
                        responseEnvelope.body != null &&
                        responseEnvelope.body.response != null)
                {
                    ArrayList<StopService> services = responseEnvelope.body.response.stop.services;
                    StopService stopService = services.get(0);
                    for (StopService s : services) {
                        if (s.getRouteDirection() == mRouteDirection) {
                            stopService = s;
                            break;
                        }
                    }
                    TripInfoCollection trips = stopService.getTripInfoCollection();
                    if(trips == null || trips.size() < 1)
                    {
                        Crouton.makeText(activity,"No vehicles currently available", Style.ALERT).show();
                    }
                    mMapFragment.loadTrips(trips);
                }
                setProgressBarIndeterminateVisibility(false);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG,error.getMessage());
                if(error.getCause().getMessage().contains("Fault")) {
                    Crouton.makeText(activity, String.format("Unable to get vehicles for this location."), Style.ALERT).show();
                }else {
                    Crouton.makeText(activity, String.format("Error: %s", error.getMessage()), Style.ALERT).show();
                }
                setProgressBarIndeterminateVisibility(false);
            }
        });
//        service.getBusLocations(mRouteId, mRouteDirection.getDirectionString(), new Callback<BusLocationResponseEnvelope>() {
//            @Override
//            public void success(BusLocationResponseEnvelope responseEnvelope, Response response) {
//                if (responseEnvelope != null &&
//                        responseEnvelope.body != null &&
//                        responseEnvelope.body.response != null)
//                {
//                    CapVehicleCollection vehicles = responseEnvelope.body.response.vehicles;
//                    if(vehicles== null || vehicles.size() < 1)
//                    {
//                        Crouton.makeText(activity,"No vehicles currently available", Style.ALERT).show();
//                    }
////                    mMapFragment.loadTrips(trips);
//                }
//                setProgressBarIndeterminateVisibility(false);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Crouton.makeText(activity, String.format("Error: %s", error.getMessage()), Style.ALERT).show();
//                setProgressBarIndeterminateVisibility(false);
//            }
//        });
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
        MenuItem direction = menu.findItem(R.id.action_direction);
        direction.setTitle(mRouteDirection == RouteDirection.North ? "North" : "South");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_direction) {
            mRouteDirection = mRouteDirection == RouteDirection.North ? RouteDirection.South : RouteDirection.North;
            invalidateOptionsMenu();
            loadRoute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStopClicked(String stopId) {
        loadRealtimeInfo(stopId);
        mMapFragment.selectStop(stopId);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        switch (itemPosition) {
            case 0:
                mRouteId = "801";
                break;
            default:
                mRouteId = "550";
                break;
        }
        mMapFragment.selectStop("");
        loadRoute();
        return true;
    }
}
