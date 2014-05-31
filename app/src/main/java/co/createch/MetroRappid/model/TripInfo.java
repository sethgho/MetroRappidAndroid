package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

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

    @Element(name = "Adherence")
    public String adherence;

    @Element(name = "Realtime")
    public TripRealtimeInfo realtimeInfo;

    @Element(name = "Block")
    public String block;

    @Element(name = "Exception")
    public String exception;

}
