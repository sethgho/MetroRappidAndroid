package co.createch.MetroRappid.model;

import android.location.Location;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import co.createch.MetroRappidAndroid.R;

/**
 * Created by Seth Gholson on 5/31/14.
 */
@Root(strict = false)
public class TripInfo implements Comparable {

    @Element(name = "Triptime")
    public String tripTime;

    @Element(name = "Tripid")
    public String tripId;

    @Element(name = "Estimatedtime")
    public String estimatedTime;

    @Element(name = "Realtime")
    public TripRealtimeInfo realtimeInfo;

    public Float distance;
    private boolean isDistanceKnown;

    public MarkerOptions getBusMarker()
    {
        MarkerOptions opts = new MarkerOptions();
        opts.draggable(false);
        opts.position(new LatLng(realtimeInfo.latitude,realtimeInfo.longitude));
        opts.title(realtimeInfo.estimatedTime);
        opts.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stopped));
        return opts;
    }

    public boolean knowsDistance() {
        return isDistanceKnown;
    }

    @Override
    public int compareTo(Object another) {
        TripInfo other = (TripInfo)another;
        if(this.distance > other.distance) {
            return 1;
        }else if(this.distance < other.distance) {
            return -1;
        }else {
            return 0;
        }
    }

    public void calculateDistanceFromLocation(Location location) {
        float[] results = new float[3];
        Location.distanceBetween(location.getLatitude(),location.getLongitude(),
                this.realtimeInfo.latitude,this.realtimeInfo.longitude, results);
        if(results.length > 0){
            isDistanceKnown = true;
            this.distance = results[0];
        }
    }

}
