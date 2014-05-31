package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class TripRealtimeInfo
{
    @Element(name="Valid")
    public String valid;

    @Element(name="Estimatedtime")
    public String estimatedTime;

    @Element(name="Polltime")
    public String pollTime;

    @Element(name="Vehicleid")
    public String vehicleId;

    @Element(name="Lat")
    public double latitude;

    @Element(name="Long")
    public double longitude;

    @Element(name="Trend")
    public String trend;

    @Element(name="Stopped")
    public String stopped;
}
