package co.createch.MetroRappid.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public class RoutePath extends ArrayList<RoutePoint> {

    public PolylineOptions getPolyLineOptions() {
        PolylineOptions rectOptions = new PolylineOptions();
        for(RoutePoint p : this)
        {
            rectOptions.add(new LatLng(p.latitude,p.longitude));
        }
        return rectOptions;
    }
}
