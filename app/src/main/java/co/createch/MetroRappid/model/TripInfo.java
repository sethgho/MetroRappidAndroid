package co.createch.MetroRappid.model;

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
public class TripInfo {

    @Element(name = "Triptime")
    public String tripTime;

    @Element(name = "Tripid")
    public String tripId;

    @Element(name = "Estimatedtime")
    public String estimatedTime;

    @Element(name = "Realtime")
    public TripRealtimeInfo realtimeInfo;

    public MarkerOptions getBusMarker()
    {
        MarkerOptions opts = new MarkerOptions();
        opts.draggable(false);
        opts.position(new LatLng(realtimeInfo.latitude,realtimeInfo.longitude));
        opts.title(realtimeInfo.estimatedTime);
        opts.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stopped));
        return opts;
    }
}
