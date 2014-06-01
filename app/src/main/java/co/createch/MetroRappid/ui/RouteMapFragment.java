package co.createch.MetroRappid.ui;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RoutePath;
import co.createch.MetroRappid.model.TripInfo;

/**
 * Created by sean on 5/31/14.
 */
public class RouteMapFragment extends SupportMapFragment implements GoogleMap.OnMapLoadedCallback, GoogleMap.OnMarkerClickListener {
    private Location mCurrentLocation;
    private RoutePath mCurrentPath;
    private CapStopCollection mCurrentStops;
    private Map<Marker, String> mStopMarkerCache;
    private OnStopClickListener mStopClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMap().setOnMapLoadedCallback(this);
        getMap().setOnMarkerClickListener(this);
    }

    public void loadRouteData(RoutePath path, CapStopCollection stops) {
        clear();
        setRoutePath(path);
        setStops(stops);
        mapZoomToShowNearestStop();
    }

    private void setRoutePath(RoutePath path) {
        if (path == null) {
            return;
        }
        mCurrentPath = path;
        getMap().addPolyline(path.getPolyLineOptions());
    }

    private void setStops(CapStopCollection stops) {
        if (stops == null) {
            return;
        }
        mCurrentStops = stops;
        final GoogleMap map = getMap();
        mStopMarkerCache = new HashMap<Marker, String>();
        Map<MarkerOptions, String> markerOptions = stops.getMarkerHashMap();
        for (MarkerOptions mo : markerOptions.keySet()) {
            Marker m = map.addMarker(mo);
            mStopMarkerCache.put(m, markerOptions.get(mo));
        }
    }

    public void setBusMarkers(Collection<MarkerOptions> markers) {
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

    public void clear() {
        getMap().clear();
    }

    @Override
    public void onMapLoaded() {
    }

    public void loadTrips(List<TripInfo> trips) {
        for (TripInfo t : trips) {
            getMap().addMarker(t.getBusMarker());
        }
    }

    private void mapZoomToShowNearestStop() {
        if (mCurrentLocation == null) {
            return;
        }
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (mStopClickListener == null) {
            return false;
        }

        String stopId = mStopMarkerCache.get(marker);
        if (stopId != null) {
            mStopClickListener.onStopClicked(stopId);
        }
        return false;
    }

    public void setOnStopClickListener(OnStopClickListener listener) {
        mStopClickListener = listener;
    }

    public interface OnStopClickListener {
        public void onStopClicked(String stopId);
    }
}
