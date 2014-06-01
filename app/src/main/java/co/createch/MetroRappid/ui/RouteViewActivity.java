package co.createch.MetroRappid.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappidAndroid.R;

public class RouteViewActivity extends BaseLocationActivity {

    public final static String TAG = RouteViewActivity.class.getName();

    public CapStop stop;
    public Location location;
    public GoogleMap map;
    private boolean isConnected = false;

    public final static String CAP_STOP = "CAP_STOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_view);




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
