package co.createch.MetroRappid.ui;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;

import co.createch.MetroRappid.model.CapStop;
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
            mapZoomToShowNearestStop();
        }
    }

    @Override
    public void onMapLoaded() {
    }

    private void mapZoomToShowNearestStop() {
        mCurrentStops.setLocation(mCurrentLocation);
        CapStop stop = mCurrentStops.get(0);
        double lat0, lon0, lat1, lon1;

        if (mCurrentLocation.getLatitude() < stop.latitude) {
            lat0 = mCurrentLocation.getLatitude();
            lat1 = stop.latitude;
        } else {
            lat0 = stop.latitude;
            lat1 = mCurrentLocation.getLatitude();
        }

        if (mCurrentLocation.getLongitude() < stop.longitude) {
            lon0 = mCurrentLocation.getLongitude();
            lon1 = stop.longitude;
        } else {
            lon0 = stop.longitude;
            lon1 = mCurrentLocation.getLongitude();
        }

        LatLng southWest = new LatLng(lat0, lon0);
        LatLng northEast = new LatLng(lat1, lon1);
        LatLngBounds latLngBox = new LatLngBounds(southWest, northEast);
        getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(
            latLngBox, 250, 250, 30));
    }

}
