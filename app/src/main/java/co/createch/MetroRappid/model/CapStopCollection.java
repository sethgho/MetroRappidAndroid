package co.createch.MetroRappid.model;

import android.location.Location;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import co.createch.MetroRappidAndroid.R;

/**
 * Created by Seth Gholson on 5/3/14.
 */
public class CapStopCollection extends ArrayList<CapStop> {

    public void setLocation(Location location) {
        for(CapStop stop : this)
        {
            stop.calculateDistanceFromLocation(location);
        }
        Collections.sort(this);
    }
//
//    public List<MarkerOptions> getMarkers()
//    {
//        ArrayList<MarkerOptions> results = new ArrayList<MarkerOptions>();
//        for(CapStop s : this)
//        {
//            results.add(s.getMarker());
//        }
//        return results;
//    }

    public Map<MarkerOptions,String> getMarkerHashMap()
    {
        Map<MarkerOptions, String> results = new HashMap<MarkerOptions, String>();
        for(CapStop s : this)
        {
            MarkerOptions option = new MarkerOptions()
                    .position(new LatLng(s.latitude,s.longitude))
                    .draggable(false)
                    .title(s.name)
                    .snippet(s.description)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.stop_red))
                    .anchor(0.5f, 0.5f);

            results.put(option, s.stopId);
        }
        return results;
    }
}
