package co.createch.MetroRappid.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import co.createch.MetroRappidAndroid.R;

/**
 * Created by Seth Gholson on 5/3/14.
 */
public class TripInfoCollection extends ArrayList<TripInfo> {

    public void setLocation(Location location) {
        for(TripInfo trip : this)
        {
            trip.calculateDistanceFromLocation(location);
        }
        Collections.sort(this);
    }

}
