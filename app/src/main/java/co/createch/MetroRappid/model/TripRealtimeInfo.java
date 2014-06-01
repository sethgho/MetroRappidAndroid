package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class TripRealtimeInfo
{
    @Element(name="Estimatedtime")
    public String estimatedTime;

    @Element(name="Polltime", required = false)
    public String pollTime;

    @Element(name="Vehicleid", required = false)
    public String vehicleId;

    @Element(name="Lat")
    public double latitude;

    @Element(name="Long")
    public double longitude;
}
