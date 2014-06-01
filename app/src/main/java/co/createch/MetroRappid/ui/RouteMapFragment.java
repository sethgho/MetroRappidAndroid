package co.createch.MetroRappid.ui;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Collection;

/**
 * Created by sean on 5/31/14.
 */
public class RouteMapFragment extends SupportMapFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setRoutePath(PolylineOptions line) {
        getMap().addPolyline(line);
    }

    public void setStopMarkers(Collection<MarkerOptions> markers) {
        final GoogleMap map = getMap();
        for (MarkerOptions m : markers) {
            map.addMarker(m);
        }
    }

    public void setLocation(Location location) {
        if (location != null) {
            getMap().setMyLocationEnabled(true);
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
        }
    }
}
