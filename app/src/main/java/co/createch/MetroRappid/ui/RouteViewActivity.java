package co.createch.MetroRappid.ui;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import co.createch.MetroRappid.MetroRapidApp;
import co.createch.MetroRappid.data.RouteRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RouteDirection;
import co.createch.MetroRappid.model.RoutePath;
import co.createch.MetroRappid.service.MetroRapidService;
import co.createch.MetroRappidAndroid.R;

public class RouteViewActivity extends BaseLocationActivity {

    public final static String TAG = RouteViewActivity.class.getName();

    public static final String ARG_ROUTE_ID = "ROUTE_ID";

    public CapStop stop;
    public Location location;
    public GoogleMap map;
    private boolean isConnected = false;
    private String mRouteId;

    public final static String CAP_STOP = "CAP_STOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_view);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            mRouteId = extras.getString(ARG_ROUTE_ID);
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
        final CapStopCollection stops = repo.getStopsForRoute(mRouteId,RouteDirection.North);
        for(MarkerOptions m : stops.getMarkers())
        {
            map.addMarker(m);
        }
    }

    private void loadRealtimeInfo(String stopId)
    {
        MetroRapidService service = MetroRapidApp.from(this).getCapMetroService();
        //TODO:
    }

    private void loadPath(RoutePath path) {
        PolylineOptions options = path.getPolyLineOptions();
        options.color(0xFF000000);
        map.addPolyline(options);
    }

    public void setMap(GoogleMap map) {
        this.map = map;
        if (isConnected) initMap();
    }

    private void initMap() {
        location = mLocationClient.getLastLocation();
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRoute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.disconnect();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

    }

    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        isConnected = true;
        if (map != null) initMap();
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
