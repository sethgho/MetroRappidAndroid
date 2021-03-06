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

import java.util.HashMap;
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
    private CapStop mSelectedStop;
    private Map<Marker, String> mStopMarkerCache;
    private OnStopClickListener mStopClickListener;
    private HashMap<String, Marker> mTripMarkers;

    public void selectStop(String stopId) {
        mSelectedStop = null;
        for (CapStop m : mCurrentStops) {
            if (stopId == m.stopId) {
                mSelectedStop = m;
                break;
            }
        }
    }

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

    public void setLocation(Location location) {
        if (location != null) {
            mCurrentLocation = location;
            getMap().setMyLocationEnabled(true);

            double[] lats = new double[2];
            double[] lons = new double[2];

            mCurrentStops.setLocation(location);
            lats[0] = mCurrentLocation.getLatitude();
            lats[1] = mCurrentStops.get(0).latitude;

            lons[0] = mCurrentLocation.getLongitude();
            lons[1] = mCurrentStops.get(0).longitude;

            mapZoomToShowCoordinates(lats, lons, false);
            selectNearestStop();
        }
    }

    public void selectNearestStop()
    {
        Marker nearestMarker = null;
        CapStop nearestStop = mCurrentStops.get(0);
        for(Marker m : mStopMarkerCache.keySet())
        {
            if(nearestStop.stopId.equals(mStopMarkerCache.get(m)))
            {
                nearestMarker = m;
            }
        }
        if(nearestMarker != null) {
            nearestMarker.showInfoWindow();
            if (mStopClickListener != null) {
                mStopClickListener.onStopClicked(nearestStop.stopId);
            }
        }
    }

    public void clear() {
        getMap().clear();
    }

    @Override
    public void onMapLoaded() {
    }

    public void loadTrips(TripInfoCollection trips) {
        if(trips == null || trips.size() == 0)
        {
            return;
        }
        //Remove existing vehicles if we have any.
        if(mTripMarkers != null)
        {
            for(Marker m : mTripMarkers.values())
            {
                m.remove();
            }
        }
        mTripMarkers = new HashMap<String, Marker>();
        for(TripInfo t : trips)
        {
            Marker m = getMap().addMarker(t.getBusMarker());
            //Cache the markers so we can remove them later.
            mTripMarkers.put(t.tripId,m);
        }

        // The rest of this depends on us having a location defined
        if (mCurrentLocation == null) {
            mCurrentStops.setLocation(mCurrentLocation);
            trips.setLocation(mCurrentLocation);

            CapStop nearStop = mSelectedStop == null ? mCurrentStops.get(0) : mSelectedStop;
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
    }

    public void mapZoomToShowNearestStop() {
        setLocation(mCurrentLocation);
    }

    private void mapZoomToShowCoordinates(double[] latitudes, double[] longitudes,
            boolean animate) {
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

        if (animate) {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(
                latLngBox, 100));
        } else {
            getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(
                latLngBox, 100));
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
