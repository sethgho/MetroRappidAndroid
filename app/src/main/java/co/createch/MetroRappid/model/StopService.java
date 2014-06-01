package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class StopService {
    @Element(name = "Route")
    public String route;

    @Element(name = "Sign")
    public String sign;

    @Element(name = "Direction")
    public String direction;

    @ElementList(inline = true, entry = "Tripinfo")
    public List<TripInfo> trips;

    public TripInfoCollection getTripInfoCollection() {
        TripInfoCollection result = new TripInfoCollection();
        for (TripInfo p : trips) {
            if (p.realtimeInfo.latitude != 0 && p.realtimeInfo.longitude != 0) {
                result.add(p);
            }
        }
        return result;
    }

}
