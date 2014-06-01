package co.createch.MetroRappid.ui;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;

import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RoutePath;

/**
 * Created by sean on 5/31/14.
 */
public class RouteMapFragment extends SupportMapFragment implements GoogleMap.OnMapLoadedCallback {
    private Location mCurrentLocation;
    private RoutePath mCurrentPath;
    private CapStopCollection mCurrentStops;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMap().setOnMapLoadedCallback(this);
    }

    public void setRoutePath(RoutePath path) {
        mCurrentPath = path;
        getMap().addPolyline(path.getPolyLineOptions());
    }

    public void setStops(CapStopCollection stops) {
        mCurrentStops = stops;
        final GoogleMap map = getMap();
        for (MarkerOptions m : stops.getMarkers()) {
            map.addMarker(m);
        }
    }

    public void setBusMarkers(Collection<MarkerOptions> markers)
    {
        final GoogleMap map = getMap();
        for (MarkerOptions m : markers) {
            map.addMarker(m);
        }
    }

    public void setLocation(Location location) {
        if (location != null) {
            mCurrentLocation = location;
            getMap().setMyLocationEnabled(true);
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
        }
    }

    @Override
    public void onMapLoaded() {

    }


}
