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

import java.util.Collections;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RoutePath;
import co.createch.MetroRappid.model.TripInfo;
import co.createch.MetroRappid.model.TripInfoCollection;

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

            mCurrentStops.setLocation(location);
            double[] lats = new double[2];
            double[] lons = new double[2];

            lats[0] = mCurrentLocation.getLatitude();
            lats[1] = mCurrentStops.get(0).latitude;

            lons[0] = mCurrentLocation.getLongitude();
            lons[1] = mCurrentStops.get(0).longitude;

            mapZoomToShowCoordinates(lats, lons, false);
        }
    }

    public void clear() {
        getMap().clear();
    }

    @Override
    public void onMapLoaded() {
    }

    public void loadTrips(TripInfoCollection trips) {
        for(TripInfo t : trips)
        {
            getMap().addMarker(t.getBusMarker());
        }

        mCurrentStops.setLocation(mCurrentLocation);
        trips.setLocation(mCurrentLocation);

        CapStop nearStop = mCurrentStops.get(0);
        TripInfo nearTrip = trips.get(0);

        double[] lats = new double[3];
        double[] lons = new double[3];

        lats[0] = mCurrentLocation.getLatitude();
        lats[1] = nearStop.latitude;
        lats[2] = nearTrip.realtimeInfo.latitude;

        lons[0] = mCurrentLocation.getLongitude();
        lons[1] = nearStop.longitude;
        lons[2] = nearTrip.realtimeInfo.longitude;

        mapZoomToShowCoordinates(lats, lons, true);

    }

    private void mapZoomToShowCoordinates(double[] latitudes, double[] longitudes,
            boolean smartZoom) {
        double lat0 = 1e28;
        double lon0 = 1e28;
        double lat1 = -1e28;
        double lon1 = -1e28;

        for (int i = 0; i < latitudes.length; i++) {
            lat0 = latitudes[i] < lat0 ? latitudes[i] : lat0;
            lat1 = latitudes[i] > lat1 ? latitudes[i] : lat1;
        }

        for (int i = 0; i < longitudes.length; i++) {
            lon0 = longitudes[i] < lon0 ? longitudes[i] : lon0;
            lon1 = longitudes[i] > lon1 ? longitudes[i] : lon1;
        }

        LatLng southWest = new LatLng(lat0, lon0);
        LatLng northEast = new LatLng(lat1, lon1);
        LatLngBounds latLngBox = new LatLngBounds(southWest, northEast);

        if (smartZoom) {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(
                latLngBox, 100));
        } else {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(
                latLngBox, 250, 250, 100));
        }
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
